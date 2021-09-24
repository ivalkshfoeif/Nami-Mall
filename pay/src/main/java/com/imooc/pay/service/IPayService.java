package com.imooc.pay.service;

import com.imooc.pay.pojo.PayInfo;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {

    /**
     * create a payment
     */
    public PayResponse create(String orderId, BigDecimal amount,  BestPayTypeEnum bestPayTypeEnum);

    /**
     * async message process
     * @param notifyData
     */
    String asyncNotify(String notifyData);

    /**
     * query payment record with Order ID
     * @param orderId
     * @return
     */
    PayInfo queryByOrderId(String orderId);
}
