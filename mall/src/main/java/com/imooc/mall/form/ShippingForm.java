package com.imooc.mall.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShippingForm {

    @NotBlank(message = "can not be empty")
    private String receiverName;

    @NotBlank(message = "can not be empty")
    private String receiverPhone;

    @NotBlank(message = "can not be empty")
    private String receiverMobile;

    @NotBlank(message = "can not be empty")
    private String receiverProvince;

    @NotBlank(message = "can not be empty")
    private String receiverCity;

    @NotBlank(message = "can not be empty")
    private String receiverDistrict;

    @NotBlank(message = "can not be empty")
    private String receiverAddress;

    @NotBlank(message = "can not be empty")
    private String receiverZip;
}
