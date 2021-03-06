package com.card.controller;

import com.card.common.Response;
import com.card.model.User;
import com.card.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户信息
 * <p>
 * Created by hongpeng.cui on 2019/3/26.
 */
@Controller
public class UserInfoController {


    @Resource
    private UserService userService;

    /**
     * 获取用户基本信息
     *
     * @param nameOrEmailOrPhoneOrId 名字 or 电话 or 邮箱
     * @param type                   1：名字 2：电话 3：邮箱 4:id
     * @return User基本信息
     */
    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public Response<User> userRegister(String nameOrEmailOrPhoneOrId, int type) {
        return userService.getUserInfo(nameOrEmailOrPhoneOrId, type);
    }


    /**
     * 用户是否已经实名
     *
     * @param nameOrEmailOrPhoneOrId 名字 or 电话 or 邮箱
     * @param type                   1：名字 2：电话 3：邮箱 4:id
     * @return TRUE
     */
    @RequestMapping(value = "/userIsRealName")
    @ResponseBody
    public Response<Boolean> userIsRealName(String nameOrEmailOrPhoneOrId, int type) {
        return userService.isUserRealName(nameOrEmailOrPhoneOrId, type);
    }


    /**
     * 用户实名操作
     *
     * @param nameOrEmailOrPhoneOrId 名字 or 电话 or 邮箱
     * @param type                   1：名字 2：电话 3：邮箱 4:id
     * @param realName               用户真名
     * @param userCode               省份证号
     * @param receiveAddress         收货地址
     * @param homeAddress            家庭住址
     * @return User信息
     */
    @RequestMapping(value = "/userRealName")
    @ResponseBody
    public Response<User> userRealName(String nameOrEmailOrPhoneOrId, int type,
                                       String realName, String userCode,
                                       String receiveAddress, String homeAddress) {
        return userService.userRealName(nameOrEmailOrPhoneOrId, type, realName, userCode, receiveAddress, homeAddress);
    }


}
