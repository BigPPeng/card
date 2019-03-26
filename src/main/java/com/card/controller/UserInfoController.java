package com.card.controller;

import com.card.common.Response;
import com.card.model.User;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息
 * <p>
 * Created by hongpeng.cui on 2019/3/26.
 */
public class UserInfoController {


    /**
     * 获取用户基本信息
     *
     * @param nameOrEmailOrPhoneOrId 名字 or 电话 or 邮箱
     * @param type                   1：名字 2：电话 3：邮箱
     * @return User基本信息
     */
    @RequestMapping(value = "/getUserInfo")
    Response<User> userRegister(String nameOrEmailOrPhoneOrId, int type) {
        Response<User> response = new Response<>();

        return response;
    }


}
