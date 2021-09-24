package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ICartServiceImplTest extends MallApplicationTests {
    
    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void add() {
        log.info("*********************NEW CART**********************");
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(27);
        cartAddForm.setSelected(true);

        ResponseVo<CartVo> add = cartService.add(cartAddForm, 1);
        log.info("add={}",gson.toJson(add));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(1);

        log.info("list={}",gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }

    @Test
    public void update(){
        CartUpdateForm cartUpdateForm = new CartUpdateForm();
        cartUpdateForm.setQuantity(5);
        cartUpdateForm.setSelected(false);
        ResponseVo<CartVo> responseVo = cartService.update(1,28,cartUpdateForm);
        log.info("update={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }

    @Test
    public void delete(){
        log.info("*********************DELETE CART**********************");
        ResponseVo<CartVo> responseVo = cartService.delete(1,28);
        log.info("delete={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());


    }

    @Test
    public void selectAll(){
        ResponseVo<CartVo> responseVo = cartService.selectAll(1);
        log.info("selectAll={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }

    @Test
    public void unSelectAll(){
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(1);
        log.info("unSelectAll={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }

    @Test
    public void sum(){
        ResponseVo<Integer> responseVo = cartService.sum(1);
        log.info("sum={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getCode());

    }
}