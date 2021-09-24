package com.imooc.mall.service.impl;


import com.google.gson.Gson;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.enums.ProductStatusEnum;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartProductVo;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(CartAddForm cartAddForm, Integer uid){
        Product product = productMapper.selectByPrimaryKey(cartAddForm.getProductId());
        // if exist
        if (product == null){
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }

        // if on sale
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())){
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SHELF_OR_DELETED);
        }

        // if qty enough
        if(product.getStock() <= 0){
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }

        //redis
        //key cart_[uid]
        //value
        // number of product which is supposed to be added to the cart
        Integer quantity = 1;
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);

        String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
        Cart cart;
        if (StringUtils.isEmpty(value)){
            cart = new Cart(product.getId(), quantity, cartAddForm.isSelected());

        }else {
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity()+1);
        }

       opsForHash.put(String.format(CART_REDIS_KEY_TEMPLATE,uid),
               String.valueOf(product.getId()),
               gson.toJson(cart));


        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid){
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        Map<String, String> entries = opsForHash.entries(redisKey);

        boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;

        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();

        for (Map.Entry<String, String> entry: entries.entrySet()){
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            //TODO need opitmizaition with mysql in
            Product product = productMapper.selectByPrimaryKey(productId);


            if (product != null){
                CartProductVo cartProductVo = new CartProductVo(productId,cart.getQuantity(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getStatus(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStock(),
                        cart.isProductSelected());
                cartProductVoList.add(cartProductVo);

                if (!cart.isProductSelected()){
                    selectAll = false;
                }
                // only choose selected
                if (cart.isProductSelected()) {
                    cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                }
            }

            cartTotalQuantity += cart.getQuantity();
        }
        cartVo.setSelectAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        return ResponseVo.success(cartVo);

    }

   @Override
   public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm){

       HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
       String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
       String value = opsForHash.get(redisKey, String.valueOf(productId));
       if (StringUtils.isEmpty(value)){
           //error: data is wrong
            return  ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
       }
       Cart cart = gson.fromJson(value, Cart.class);
       if (cartUpdateForm.getQuantity() != null && cartUpdateForm.getQuantity() >= 0){
           cart.setQuantity(cartUpdateForm.getQuantity());
       }
       if (cartUpdateForm.getSelected() != null){
           cart.setProductSelected(cartUpdateForm.getSelected());
       }
       opsForHash.put(redisKey,String.valueOf(productId),gson.toJson(cart));
       return list(uid);

   }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)){
            //error: data is wrong
            return  ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        Cart cart = gson.fromJson(value, Cart.class);

        opsForHash.delete(redisKey,String.valueOf(productId),gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);

        for (Cart cart: listForCart(uid)){
            cart.setProductSelected(true);
            opsForHash.put(redisKey,
                    String.valueOf(cart.getProductId()),
                    gson.toJson(cart));

        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);

        for (Cart cart: listForCart(uid)){
            cart.setProductSelected(false);
            opsForHash.put(redisKey,
                    String.valueOf(cart.getProductId()),
                    gson.toJson(cart));

        }
        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer sum =  listForCart(uid).stream().
                map(Cart::getQuantity).
                reduce(0, Integer::sum);
        return ResponseVo.success(sum);
    }

    public List<Cart> listForCart(Integer uid){
        List<Cart> cartList = new ArrayList<>();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        Map<String, String> entries = opsForHash.entries(redisKey);

        for (Map.Entry<String, String> entry: entries.entrySet()) {
            cartList.add(gson.fromJson(entry.getValue(), Cart.class));
        }

        return cartList;
    }
}
