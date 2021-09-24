package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.service.IShippingService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class ShippingServiceImplTest extends MallApplicationTests {

    @Autowired
    private IShippingService shippingService;

    private ShippingForm shippingForm;

    private Integer shippingId;

    @Before
    public void before(){
        ShippingForm shippingForm = new ShippingForm();
        shippingForm.setReceiverName("muxing lin");
        shippingForm.setReceiverAddress("1126 Bolyston St");
        shippingForm.setReceiverCity("Boston");
        shippingForm.setReceiverMobile("6178188637");
        shippingForm.setReceiverProvince("MA");
        shippingForm.setReceiverDistrict("some county");
        shippingForm.setReceiverZip("02215");
        this.shippingForm =shippingForm;
        this.shippingId = 10;

        add();

    }

    private Gson gson = new Gson();

    private Integer uid = 1;


    public void add(){
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, shippingForm);
        log.info("add={}",gson.toJson(responseVo));
        this.shippingId = responseVo.getData().get("shippingId");
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }
    @After
    public void delete(){
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("delete={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void update(){
        shippingForm.setReceiverZip("31800");
        ResponseVo responseVo = shippingService.update(uid, shippingId, shippingForm);
        log.info("update={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void list(){
        ResponseVo responseVo = shippingService.list(uid, 1, 10);
        log.info("list={}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

}