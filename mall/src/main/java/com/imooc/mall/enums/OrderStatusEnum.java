package com.imooc.mall.enums;


import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CANCELLED(1, "the order has been cancelled"),

    UNPAID(10, "unpaid"),

    PAID(20, "PAID"),

    SHIPPED(40,"shipped"),

    TRADE_SUCCESS(50, "payment success"),

    TRADE_CLOSE(60, "payment close"),
    ;

    Integer code;

    String description;

    OrderStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
