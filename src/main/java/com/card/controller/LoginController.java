package com.card.controller;

import com.card.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录注册模块
 *
 * Created by hongpeng.cui on 2019/3/26.
 */
@Controller
@RequestMapping(value = "/LoginController")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);



    @RequestMapping(value = "/getCheckCode")
    public Response<String> getEmailCheckCode(String email){
        Response<String> response = new Response<>();

        return response;
    }

    @RequestMapping(value = "/verifyCheckCode")
    public Response<Boolean> verifyEmailCheckCode(String email,String code) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    @RequestMapping(value = "/verifyUserInfo")
    public Response<Boolean> verifyUserInfoBeforeRes(String userName,String email,String phoneNumber) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    @RequestMapping(value = "/userRegister")
    public Response<Boolean> userRegister(String userName,String email,String phoneNumber, String password) {
        Response<Boolean> response = new Response<>();

        return response;
    }

    @RequestMapping(value = "/userLogin")
    Response<Boolean> userLogin(String nameOrEmailOrPhone,String password,int type) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    @RequestMapping(value = "/userLogOut")
    Response<Boolean> userLogOut(String nameOrEmailOrPhone,int type) {
        Response<Boolean> response = new Response<>();

        return response;
    }



}
