package com.card.controller;

import com.card.common.Response;
import com.card.model.User;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息
 *
 * Created by hongpeng.cui on 2019/3/26.
 */
public class UserInfoController {


    @RequestMapping(value = "/getUserInfo")
    Response<User> userRegister(String nameOrEmailOrPhoneOrId, int type) {
        Response<User> response = new Response<>();

        return response;
    }



}
