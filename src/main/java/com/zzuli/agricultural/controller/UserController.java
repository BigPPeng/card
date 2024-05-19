package com.zzuli.agricultural.controller;

import com.card.common.Response;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import com.zzuli.agricultural.service.UserServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceV2 userServiceV2;

    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public Response<User> userLogin(String userName, String userPass) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPass)) {
            return new Response<>("用户名或者密码为空", 1);
        }
        User user = userServiceV2.userLogin(userName, userPass);
        return user == null
                ? new Response<>("用户名或者密码错误", 1)
                : new Response<>("成功", 0, user);
    }


    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public Response<Boolean> deleteUser(int id) {
        userServiceV2.deleteUser(id);
        return new Response<>("成功", 0, Boolean.TRUE);
    }

    @RequestMapping(value = "/adminAdd")
    @ResponseBody
    public Response<String> addAdministrators(int adminUserid, String userName, String userPass, int userType) {
        UserTypeEnum byId = UserTypeEnum.getById(userType);
        String result = "用户类型不对";
        if (byId == UserTypeEnum.administrators) {
            result = userServiceV2.addAdministrators(adminUserid, userName, "", "", userPass);
        }
        if (byId == UserTypeEnum.agricultural_technology_experts) {
            result = userServiceV2.addAgriculturalTechnologyExperts(adminUserid, userName, "", "", userPass);
        }
        if (byId == UserTypeEnum.Store_owner || byId == UserTypeEnum.buyer) {
            result = userServiceV2.addStoreOwner(adminUserid, userName, "", "", userPass, byId);
        }
        if (UserServiceV2.SUCCESS.equals(result)) {
            return new Response<>("成功", 0, result);
        }
        return new Response<>("失败", 1, result);
    }

    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public Response<String> userRegister(String userName, String userPhone, String userEmail,
                                         String userPass, int userType) {
        UserTypeEnum byId = UserTypeEnum.getById(userType);
        String result = userServiceV2.userRegister(userName, userPhone, userEmail, userPass, byId);
        if (UserServiceV2.SUCCESS.equals(result)) {
            return new Response<>("成功", 0, result);
        }
        return new Response<>("失败", 1, result);
    }

    @RequestMapping(value = "/getAllUserByType")
    @ResponseBody
    public Response<List<User>> getAllUserByType(List<Integer> userTypeEnums) {
        List<UserTypeEnum> typeEnums = Optional.ofNullable(userTypeEnums).orElse(Collections.emptyList())
                .stream().map(UserTypeEnum::getById).collect(Collectors.toList());
        List<User> allUserByType = userServiceV2.getAllUserByType(typeEnums);
        return new Response<>("成功", 0, allUserByType);
    }

    @RequestMapping(value = "/getUserById")
    @ResponseBody
    public Response<User> getUserById(int id) {
        User userById = userServiceV2.getUserById(id);
        if (userById == null) {
            return new Response<>("失败", 1, null);
        }
        return new Response<>("成功", 0, userById);
    }

}
