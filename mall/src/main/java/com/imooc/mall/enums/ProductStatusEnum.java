package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ON_SALE(1),

    OFF_SHELF(2),

    DELETED(3),

    ;

    Integer code;

    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
