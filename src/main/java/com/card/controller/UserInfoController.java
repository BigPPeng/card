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
        return userService.getUserInfo(nameOrEmailOrPhoneOrId,type);
    }


    /*
     * 1：名字 2：电话 3：邮箱 4:id
     */
    @RequestMapping(value = "/userIsRealName")
    @ResponseBody
    public Response<Boolean> userIsRealName(String nameOrEmailOrPhoneOrId, int type) {
        return userService.isUserRealName(nameOrEmailOrPhoneOrId,type);
    }



    //1：名字 2：电话 3：邮箱 4:id
    @RequestMapping(value = "/userRealName")
    @ResponseBody
    public Response<User> userRealName(String nameOrEmailOrPhoneOrId, int type,
                                       String realName, String userCode,
                                       String receiveAddress, String homeAddress) {
        return userService.userRealName(nameOrEmailOrPhoneOrId,type,realName,userCode,receiveAddress,homeAddress);
    }





}
