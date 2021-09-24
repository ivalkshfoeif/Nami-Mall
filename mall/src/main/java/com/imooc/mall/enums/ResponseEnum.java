package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(-1,"server error"),

    SUCCESS(0,"success"),

    PASSWORD_ERROR(1,"wrong password"),

    USERNAME_EXIST(2, "username already exists"),

    PARAM_ERROR(3,"parameters error"),

    EMAIL_EXIST(4,"email already exists"),

    NEED_LOGIN(10,"please login first"),

    USERNAME_OR_PASSWORD_ERROR(11,"wrong username or password"),

    PRODUCT_OFF_SHELF_OR_DELETED(12, "the product is off shelf or deleted"),

    PRODUCT_NOT_EXIST(13,"the product does not exist"),

    PRODUCT_STOCK_ERROR(14,"stock is not enough"),

    CART_PRODUCT_NOT_EXIST(15,"the product does not exist in the cart"),

    DELETE_SHIPPING_FAIL(16, "fail to delete the shipping address"),

    SHIPPING_NOT_EXIST(17, "shipping address does not exist"),

    CART_SELECTED_IS_EMPTY(18, "please select the product before making an order"),

    ORDER_NOT_EXIST(19,"the order does not exist"),

    ORDER_STATUS_ERROR(20,"order status is not supported")

    ;

    Integer code;

    String description;

    ResponseEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
