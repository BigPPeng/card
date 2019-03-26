package com.card.controller;

import com.card.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录注册模块
 * <p>
 * Created by hongpeng.cui on 2019/3/26.
 */
@Controller
@RequestMapping(value = "/LoginController")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * 返回值为6位数字验证码，验证可以在前端做，也可以在后台
     *
     * @param email 邮箱地址 or 手机号
     * @return 6位验证码
     */
    @RequestMapping(value = "/getCheckCode")
    public Response<String> getEmailCheckCode(String email) {
        Response<String> response = new Response<>();

        return response;
    }

    /**
     * 验证用户验证码是否正确
     *
     * @param email 邮箱地址 or 手机号
     * @param code  用户输入的验证码
     * @return true：正确
     */
    @RequestMapping(value = "/verifyCheckCode")
    public Response<Boolean> verifyEmailCheckCode(String email, String code) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    /**
     * 验证注册用户的用户名，邮箱，电话是否可以注册，三者不能与现有的重复注册
     * 重复则返回 false
     *
     * @param userName    用户名
     * @param email       邮箱
     * @param phoneNumber 电话
     * @return true：可以注册，false：看code类型，message为原因
     */
    @RequestMapping(value = "/verifyUserInfo")
    public Response<Boolean> verifyUserInfoBeforeRes(String userName, String email, String phoneNumber) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    @RequestMapping(value = "/userRegister")
    public Response<Boolean> userRegister(String userName, String email, String phoneNumber, String password) {
        Response<Boolean> response = new Response<>();

        return response;
    }

    /**
     * 用户可以根据名字，电话，邮箱 + 密码 进行登陆
     *
     * @param nameOrEmailOrPhone 名字 or 电话 or 邮箱
     * @param password           密码
     * @param type               1：名字 2：电话 3：邮箱
     * @return 成功 true
     */
    @RequestMapping(value = "/userLogin")
    Response<Boolean> userLogin(String nameOrEmailOrPhone, String password, int type) {
        Response<Boolean> response = new Response<>();

        return response;
    }


    /**
     * 用户可以根据名字，电话，邮箱  进行登陆
     *
     * @param nameOrEmailOrPhone 名字 or 电话 or 邮箱
     * @param type               1：名字 2：电话 3：邮箱
     * @return 成功 true
     */
    @RequestMapping(value = "/userLogOut")
    Response<Boolean> userLogOut(String nameOrEmailOrPhone, int type) {
        Response<Boolean> response = new Response<>();

        return response;
    }


}
