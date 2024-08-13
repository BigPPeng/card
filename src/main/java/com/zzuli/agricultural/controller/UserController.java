package com.zzuli.agricultural.controller;

import com.alibaba.fastjson.JSON;
import com.zzuli.agricultural.common.Response;
import com.zzuli.agricultural.model.request.*;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import com.zzuli.agricultural.service.UserServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceV2 userServiceV2;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Response<User> userLogin(@RequestBody UserLoginReq userInfo) {
        String userName = userInfo.getUserName();
        String userPass = userInfo.getUserPass();
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
    public Response<Boolean> deleteUser(@RequestBody IdReq idReq) {
        userServiceV2.deleteUser(idReq.getId());
        return new Response<>("成功", 0, Boolean.TRUE);
    }

    @RequestMapping(value = "/adminAdd")
    @ResponseBody
    public Response<String> addAdministrators(@RequestBody AddAdministratorsReq req) {
        UserTypeEnum byId = UserTypeEnum.getById(req.getUserType());
        String result = "用户类型不对";
        if (byId == UserTypeEnum.administrators) {
            result = userServiceV2.addAdministrators(req.getAdminUserid(), req.getUserName(), "", "", req.getUserPass());
        }
        if (byId == UserTypeEnum.agricultural_technology_experts) {
            result = userServiceV2.addAgriculturalTechnologyExperts(req.getAdminUserid(), req.getUserName(), "", "", req.getUserPass());
        }
        if (byId == UserTypeEnum.Store_owner || byId == UserTypeEnum.buyer) {
            result = userServiceV2.addStoreOwner(req.getAdminUserid(), req.getUserName(), "", "", req.getUserPass(), byId);
        }
        if (UserServiceV2.SUCCESS.equals(result)) {
            return new Response<>("成功", 0, result);
        }
        return new Response<>("失败", 1, result);
    }

    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public Response<String> userRegister(@RequestBody UserRegReq userType) {
        UserTypeEnum byId = UserTypeEnum.getById(userType.getUserType());
        String result = userServiceV2.userRegister(userType.getUserName(), userType.getUserPhone(),
                userType.getUserEmail(), userType.getUserPass(), byId);
        if (UserServiceV2.SUCCESS.equals(result)) {
            return new Response<>("成功", 0, result);
        }
        return new Response<>("失败", 1, result);
    }

    @RequestMapping(value = "/getAllUserByType")
    @ResponseBody
    public Response<List<User>> getAllUserByType(@RequestBody ListReq<Integer> listReq) {
        log.info("getAllUserByType{}", JSON.toJSONString(listReq));
        List<UserTypeEnum> typeEnums = Optional.ofNullable(listReq.getList()).orElse(Collections.emptyList())
                .stream().map(UserTypeEnum::getById).collect(Collectors.toList());
        List<User> allUserByType = userServiceV2.getAllUserByType(typeEnums);
        log.info("getAllUserByType:"+ JSON.toJSONString(allUserByType));
        return new Response<>("成功", 0, allUserByType);
    }

    @RequestMapping(value = "/getUserById")
    @ResponseBody
    public Response<User> getUserById(@RequestBody IdReq idReq) {
        User userById = userServiceV2.getUserById(idReq.getId());
        if (userById == null) {
            return new Response<>("失败", 1, null);
        }
        return new Response<>("成功", 0, userById);
    }

}
