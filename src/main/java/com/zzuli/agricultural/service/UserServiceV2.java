package com.zzuli.agricultural.service;

import com.zzuli.agricultural.common.DateToolUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.UserMapper2;
import com.zzuli.agricultural.model.User;
import com.zzuli.agricultural.model.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceV2 {

    public static String SUCCESS = "success";
    public static String FAIL = "fail";

    @Autowired
    private UserMapper2 userMapper2;

    public User userLogin(String userName, String userPass) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("usernameSelect", userName);
        param.put("password", userPass);
        List<User> users = userMapper2.selectUserByParams(param);
        if (!CollectionUtils.isEmpty(users) && users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    public boolean deleteUser(int id) {
        userMapper2.deleteUser(id);
        return true;
    }

    public String addAdministrators(int adminUserid, String userName, String userPhone, String userEmail, String userPass) {
        if (!isAdmin(adminUserid)) {
            return "当前用户不是管理员，不能添加";
        }
        if (!CollectionUtils.isEmpty(getByName(userName))) {
            return "用户名重复，请求修用户名";
        }
        return userRegister(userName, userPhone, userEmail, userPass, UserTypeEnum.administrators);
    }

    public String addStoreOwner(int adminUserid, String userName, String userPhone, String userEmail, String userPass, UserTypeEnum byId) {
        if (!isAdmin(adminUserid)) {
            return "当前用户不是管理员，不能添加";
        }
        if (!CollectionUtils.isEmpty(getByName(userName))) {
            return "用户名重复，请求修用户名";
        }
        return userRegister(userName, userPhone, userEmail, userPass, byId);
    }

    public String addAgriculturalTechnologyExperts(int adminUserid, String userName, String userPhone, String userEmail, String userPass) {
        if (!isAdmin(adminUserid)) {
            return "当前用户不是管理员，不能添加";
        }
        if (!CollectionUtils.isEmpty(getByName(userName))) {
            return "用户名重复，请求修用户名";
        }
        return userRegister(userName, userPhone, userEmail, userPass, UserTypeEnum.agricultural_technology_experts);
    }

    private boolean isAdmin(int id) {
        User allUserById = getUserById(id);
        if (allUserById == null || allUserById.getUserType() != UserTypeEnum.administrators.type) {
            return false;
        }
        return true;
    }

    public String userRegister(String userName, String userPhone, String userEmail,
                                String userPass, UserTypeEnum userTypeEnum) {
        if (!CollectionUtils.isEmpty(getByName(userName))) {
            return "用户名重复，请求修用户名";
        }
        try {
            User build = User.builder()
                    .username(userName)
                    .phone(Optional.ofNullable(userPhone).orElse(""))
                    .email(Optional.ofNullable(userEmail).orElse(""))
                    .password(userPass)
                    .userType(userTypeEnum.type)
                    .registerTime(DateToolUtil.getNow())
                    .userStatus(1)
                    .isActive(1).build();
            userMapper2.insertUser(build);
        } catch (Exception e) {
            log.info("注册用户失败，报错了", e);
            return FAIL;
        }
        return SUCCESS;
    }



    public List<User> getAllUserByType(List<UserTypeEnum> userTypeEnums) {
        List<User> users = userMapper2.selectAllUsers();
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        if (CollectionUtils.isEmpty(userTypeEnums)) {
            return users;
        }
        List<User> userTarget = Lists.newArrayList();
        for (User user : users) {
            if (userTypeEnums.contains(UserTypeEnum.getById(user.getUserType()))) {
                userTarget.add(user);
            }
        }
        return userTarget;
    }

    public User getUserById(int id) {
        return userMapper2.selectUser(id);
    }

    private List<User> getByName(String userName) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("usernameSelect", userName);
        return userMapper2.selectUserByParams(param);
    }


}
