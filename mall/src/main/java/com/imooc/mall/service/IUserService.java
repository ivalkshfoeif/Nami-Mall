package com.imooc.mall.service;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;

public interface IUserService {

    /**
     * register
     */

    ResponseVo<User> register(User user);

    /**
     * login
     */
    ResponseVo<User> login(String username, String password);


}
