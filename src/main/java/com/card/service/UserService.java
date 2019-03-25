package com.card.service;

import com.card.common.Response;
import com.card.mapper.UserMapper;
import com.card.model.User;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 用户服务
 *
 * Created by hongpeng.cui on 2019/3/25.
 */
public class UserService {

    @Resource
    private UserMapper userMapper;


    public Response insertUser(User user) {
        Response<Long> response = new Response<>();
        if (user == null) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        if (Strings.isNullOrEmpty(user.getName())
                || Strings.isNullOrEmpty(user.getPass())
                || Strings.isNullOrEmpty(user.getPhoneNumber())
                || Strings.isNullOrEmpty(user.getUserEmail())) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        Map<String,Object> param = Maps.newHashMap();
        param.put("name",user.getName());
        if (userMapper.getUserCount(param) > 0) {
            response.setStatus(2);
            response.setMessage("用户名存在");
            return response;
        }

        param.clear();
        param.put("userEmail",user.getUserEmail());
        if (userMapper.getUserCount(param) > 0) {
            response.setStatus(2);
            response.setMessage("邮箱已经注册");
            return response;
        }

        param.clear();
        param.put("phoneNumber",user.getUserEmail());
        if (userMapper.getUserCount(param) > 0) {
            response.setStatus(2);
            response.setMessage("手机号已经注册");
            return response;
        }

        user.setLoginTime(new Date());
        user.setCreateTime(new Date());
        response.setData(userMapper.insert(user));

        return response;
    }

    public Response updateUser(User user) {
        Response<Integer> response = new Response<>();
        if (user == null || user.getId() < 0) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        Map<String,Object> param = Maps.newHashMap();

        if (user.getName() != null) {
            param.put("name",user.getName());
            if (userMapper.getUserCount(param) > 0) {
                response.setStatus(2);
                response.setMessage("用户名存在");
                return response;
            }
        }
        if (user.getUserEmail() != null) {
            param.clear();
            param.put("userEmail",user.getUserEmail());
            if (userMapper.getUserCount(param) > 0) {
                response.setStatus(2);
                response.setMessage("邮箱已经注册");
                return response;
            }
        }

        if (user.getPhoneNumber() != null){
            param.clear();
            param.put("phoneNumber",user.getUserEmail());
            if (userMapper.getUserCount(param) > 0) {
                response.setStatus(2);
                response.setMessage("手机号已经注册");
                return response;
            }
        }

        response.setData(userMapper.updateByPrimaryKey(user));

        return response;
    }

    public boolean userLogin(String name,String pass) {
        return false;
    }


}
