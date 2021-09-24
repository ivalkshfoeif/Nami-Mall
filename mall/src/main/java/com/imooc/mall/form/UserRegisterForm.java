package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterForm {

    @NotBlank(message = "can not be empty")
    private String username;

    @NotBlank(message = "can not be empty")
    private String password;

    @NotBlank(message = "can not be empty")
    private String email;

}
