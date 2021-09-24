package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.dao.OrderItemMapper;
import com.imooc.mall.dao.OrderMapper;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.dao.ShippingMapper;
import com.imooc.mall.enums.OrderStatusEnum;
import com.imooc.mall.enums.PaymentTypeEnum;
import com.imooc.mall.enums.ProductStatusEnum;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.pojo.*;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.service.IOrderService;
import com.imooc.mall.vo.OrderItemVo;
import com.imooc.mall.vo.OrderVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {
        //verify the shipping address
        Shipping shipping = shippingMapper.selectByUidAndShippingId(uid,shippingId);
        if (shipping == null){
            return ResponseVo.error(ResponseEnum.SHIPPING_NOT_EXIST);
        }

        //get shopping cart(if product exist)

        List<Cart> cartList = cartService.listForCart(uid).stream()
                .filter(Cart::isProductSelected)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(cartList)){
            return ResponseVo.error(ResponseEnum.CART_SELECTED_IS_EMPTY);
        }

        //get productIds in cartList

        Set<Integer> productIdSet = cartList.stream()
                .map(Cart::getProductId)
                .collect(Collectors.toSet());


        List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
        Long orderNo = generateOrderNo();
        //productId -> product

        Map<Integer, Product> map = productList.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        List<OrderItem> orderItemList = new ArrayList<>();
        for (Cart cart: cartList){
            //database select with productId
            Product product = map.get(cart.getProductId());
            if (product == null){
                return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST,
                        "product do not exist. productId = " + cart.getProductId());
            }

            //get shopping cart(is status shows available)
            if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())){
                return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SHELF_OR_DELETED,
                        "product " + product.getName() +" is not on sale");
            }

            //get shopping cart(is stock enough)
            if (product.getStock() < cart.getQuantity()){
                return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR,
                        "stock is not enough for "+product.getName());
            }
            //calculate total price of selected products
            OrderItem orderItem = buildOrderItem(uid, orderNo, cart.getQuantity(), product);
            //form the order
            orderItemList.add(orderItem);
            //change the stock
            product.setStock(product.getStock() - cart.getQuantity());
            int resultForProductStock = productMapper.updateByPrimaryKey(product);
            if (resultForProductStock <= 0){
                return ResponseVo.error(ResponseEnum.ERROR);

            }

        }
        Order order = buildOrder(uid, orderNo, shippingId, orderItemList);

        //order order_item table data insert

        int result = orderMapper.insertSelective(order);
        if (result <= 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        int resultForOrderItem = orderItemMapper.batchInsert(orderItemList);

        if (resultForOrderItem <= 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        //delete the selected product from shopping cart
        //not in the above loop to make sure all data is correctly inserted
        //redis can not rollback
        for (Cart cart: cartList){
            cartService.delete(uid,cart.getProductId());
        }


        //build OrderVo
        OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);


        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectByUid(uid);

        Set<Long> orderNoSet = orderList.stream().
                map(Order::getOrderNo).
                collect(Collectors.toSet());

        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(orderNoSet);
        // orderNo -> orderItemList
        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrderNo));

        Set<Integer> shippingIdSet = orderList.stream().
                map(Order::getShippingId).
                collect(Collectors.toSet());

        List<Shipping> shippingList = shippingMapper.selectByIdSet(shippingIdSet);
        // shippingId -> shipping
        Map<Integer, Shipping> shippingMap = shippingList.stream()
                .collect(Collectors.toMap(Shipping::getId,shipping -> shipping));


        List<OrderVo> orderVoList = new ArrayList<>();

        for (Order order: orderList){
            OrderVo orderVo = buildOrderVo(order,
                    orderItemMap.get(order.getOrderNo()),
                    shippingMap.get(order.getShippingId()));
            orderVoList.add(orderVo);
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(orderVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<OrderVo> detail(Integer uid, Long orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)){
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST);
        }
        Set<Long> orderNoSet = new HashSet<>();
        orderNoSet.add(order.getOrderNo());
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(orderNoSet);

        Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());


        OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);
        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo cancel(Integer uid, Long orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)){
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST);
        }
        // only can be cancelled when it is unpaid
        if (!order.getStatus().equals(OrderStatusEnum.UNPAID.getCode())){
            return ResponseVo.error(ResponseEnum.ORDER_STATUS_ERROR);
        }
        order.setStatus(OrderStatusEnum.CANCELLED.getCode());
        order.setCloseTime(new Date());
        int result = orderMapper.updateByPrimaryKeySelective(order);
        if (result <= 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();

    }

    @Override
    public void paid(Long orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null ){
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getDescription() + " Order Id: " + orderNo);
        }
        // only can be changed to paid when it is unpaid
        if (!order.getStatus().equals(OrderStatusEnum.UNPAID.getCode())){
            throw new RuntimeException(ResponseEnum.ORDER_STATUS_ERROR.getDescription()+ " Order Id: " + orderNo);
        }
        order.setStatus(OrderStatusEnum.PAID.getCode());
        order.setCloseTime(new Date());
        int result = orderMapper.updateByPrimaryKeySelective(order);
        if (result <= 0){
            throw new RuntimeException("fail to update the order status from unpaid to paid" + " Order Id: " + orderNo);
        }

    }

    private OrderVo buildOrderVo(Order order, List<OrderItem> orderItemList, Shipping shipping) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order,orderVo);

        OrderItemVo orderItemVo = new OrderItemVo();

        List<OrderItemVo> orderItemVoList = orderItemList.stream().map(e -> {
            BeanUtils.copyProperties(e, orderItemVo);
            return orderItemVo;
        }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);

        if (shipping != null) {
            orderVo.setShippingId(shipping.getId());
            orderVo.setShippingVo(shipping);
        }
        return orderVo;

    }

    private Order buildOrder(Integer uid,
                            Long orderNo,
                            Integer shippingId,
                            List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(uid);
        order.setShippingId(shippingId);
        order.setPayment(orderItemList.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        order.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
        order.setPostage(0);
        order.setStatus(OrderStatusEnum.UNPAID.getCode());
        return order;

    }


    private Long generateOrderNo() {
        return System.currentTimeMillis() + new Random().nextInt(999);
    }

    private OrderItem buildOrderItem(Integer uid, Long orderNo, Integer quantity, Product product) {
        OrderItem item = new OrderItem();
        item.setUserId(uid);
        item.setOrderNo(orderNo);
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setProductImage(product.getMainImage());
        item.setCurrentUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return item;
    }
}
