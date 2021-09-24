package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartAddForm {

    @NotNull(message = "can not be empty")
    private Integer productId;

    //@NotBlank(message = "can not be empty")
    private boolean selected = true;




}
