package com.imooc.mall.pojo;

import lombok.Data;

@Data
public class Cart {
    private Integer productId;

    private Integer quantity;

    private boolean productSelected;

    public Cart(Integer productId, Integer quantity, boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSelected = productSelected;
    }

    public Cart() {
    }
}
