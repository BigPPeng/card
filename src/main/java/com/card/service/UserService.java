package com.card.service;

import com.alibaba.fastjson.JSON;
import com.card.common.CardUtil;
import com.card.common.Response;
import com.card.mapper.UserMapper;
import com.card.model.User;
import com.card.model.enums.UserStatus;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 *
 * Created by hongpeng.cui on 2019/3/25.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;


    /**
     * @param nameOrEmailOrPhoneOrId 名字 or 电话 or 邮箱
     * @param type                   1：名字 2：电话 3：邮箱 4:id
     * @return User基本信息
     */
    public Response<User> getUserInfo(String nameOrEmailOrPhoneOrId, int type) {
        Response<User> userResponse = new Response<>();
        if (type == 4 && CardUtil.isNumber(nameOrEmailOrPhoneOrId)) {
            Long id = Long.parseLong(nameOrEmailOrPhoneOrId);
            User user = userMapper.selectByPrimaryKey(id);
            if (user == null) {
                userResponse.setStatus(1);
                userResponse.setMessage("参数错误");
                return userResponse;
            }
            userResponse.setData(user);
            return userResponse;

        }

        Map<String,Object> param = Maps.newHashMap();
        if (type == 1) {
            param.put("nameSelect",nameOrEmailOrPhoneOrId);
        }
        if (type == 2 && CardUtil.isPhoneNumber(nameOrEmailOrPhoneOrId)) {
            param.put("phoneNumberSelect",nameOrEmailOrPhoneOrId);
        }
        if (type == 3 && CardUtil.isEmail(nameOrEmailOrPhoneOrId)) {
            param.put("userEmailSelect",nameOrEmailOrPhoneOrId);
        }
        if (param.isEmpty()) {
            userResponse.setStatus(1);
            userResponse.setMessage("参数类型错误");
            return userResponse;
        }

        List<User> list = userMapper.selectUserByParams(param);
        if (CollectionUtils.isEmpty(list) || list.size() != 1) {
            userResponse.setStatus(1);
            userResponse.setMessage("参数错误");
            return userResponse;
        }
        userResponse.setData(list.get(0));
        return userResponse;


    }


    public Response<Boolean> isUserRealName(String nameOrEmailOrPhoneOrId, int type) {
        Response<Boolean> response = new Response<>();

        Response<User> temp = this.getUserInfo(nameOrEmailOrPhoneOrId,type);
        if (temp.getStatus() != 0 || temp.getData() == null || temp.getData().getStatus() != UserStatus.OK.status) {
            response.setMessage("不存在用户或者未实名");
            response.setStatus(1);
            response.setData(Boolean.FALSE);
            return response;
        }

        response.setData(Boolean.TRUE);
        return response;

    }

    public Response<User> userRealName(String nameOrEmailOrPhoneOrId, int type,String realName, String userCode, String receiveAddress, String homeAddress) {
        Response<User> response = new Response<>();
        if (Strings.isNullOrEmpty(realName) || Strings.isNullOrEmpty(userCode)) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        Response<User> temp = this.getUserInfo(nameOrEmailOrPhoneOrId,type);
        if (temp.getStatus() != 0 || temp.getData() == null) {
            response.setMessage("没有用户信息，message:"+temp.getMessage());
            response.setStatus(1);
            response.setData(null);
            return response;
        }
        if (temp.getData().getStatus() == UserStatus.OK.status) {
            response.setMessage("已经实名，无需重复");
            response.setStatus(0);
            response.setData(null);
            return response;
        }


        User user = temp.getData();
        user.setStatus(UserStatus.OK.status);
        user.setRealName(Strings.isNullOrEmpty(realName) ? "":realName);
        user.setHomeAddress(Strings.isNullOrEmpty(homeAddress) ? "" :homeAddress);
        user.setReceiveAddress(Strings.isNullOrEmpty(receiveAddress) ? "" : receiveAddress);
        user.setIdentityCardNumber(userCode);
        Response upResponse = this.updateUser(user);
        if (upResponse.getStatus() != 0) {
            response.setStatus(1);
            response.setMessage("实名失败，请检查信息"+upResponse.getMessage());
            return response;
        }

        response.setData(user);
        return response;

    }


    public Response<Long> insertUser(User user) {
        logger.info("request:{}", JSON.toJSONString(user));
        Response<Long> response = new Response<>();
        if (user == null) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        if (Strings.isNullOrEmpty(user.getName())
                || Strings.isNullOrEmpty(user.getPass())
                || Strings.isNullOrEmpty(user.getPhoneNumber())
                || Strings.isNullOrEmpty(user.getUserEmail())
                || !CardUtil.isPhoneNumber(user.getPhoneNumber())
                || !CardUtil.isEmail(user.getUserEmail())) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        Map<String,Object> param = Maps.newHashMap();
        param.put("nameSelect",user.getName());
        if (userMapper.getUserCount(param) > 0) {
            response.setStatus(2);
            response.setMessage("用户名存在");
            return response;
        }

        param.clear();
        param.put("userEmailSelect",user.getUserEmail());
        if (userMapper.getUserCount(param) > 0) {
            response.setStatus(2);
            response.setMessage("邮箱已经注册");
            return response;
        }

        param.clear();
        param.put("phoneNumberSelect",user.getPhoneNumber());
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
            response.setMessage("updateUser 参数错误");
            return response;
        }
        Response<User> responseUser = this.getUserInfo(String.valueOf(user.getId()),4);
        if (responseUser.getStatus() != 0) {
            response.setStatus(1);
            response.setMessage("updateUser 用户信息错误，"+responseUser.getMessage());
            return response;
        }
        response.setData(userMapper.updateByPrimaryKey(user));

        return response;
    }

    /**
     *
     * @param info 信息
     * @param pass 密码
     * @param type  1：名字 2：电话 3：邮箱
     * @return 结果
     */
    public Response<Boolean> userLogin(String info,String pass,int type) {
        Response<Boolean> response = new Response<>();
        if (type != 1 && type != 2 && type != 3) {
            response.setStatus(1);
            response.setMessage("登陆类型错误");
            response.setData(false);
            return response;
        }
        Response<User> userResponse = this.getUserInfo(info,type);
        if (userResponse.getStatus() != 0) {
            response.setStatus(userResponse.getStatus());
            response.setMessage(userResponse.getMessage());
            response.setData(false);
            return response;
        }
        User user = userResponse.getData();
        if (user.getStatus() == UserStatus.LIMIT.status) {
            response.setStatus(1);
            response.setMessage("已经限制登陆");
            response.setData(false);
            return response;
        }
        if (user.getPass().equals(pass)) {
            response.setData(true);
        } else {
            response.setStatus(1);
            response.setMessage("用户名密码错误");
            response.setData(false);
        }
        return response;
    }

    public Response<Integer> getCount(Map<String,Object> param) {
        Response<Integer> response = new Response<>();
        if (param == null || param.isEmpty()) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        response.setData(userMapper.getUserCount(param));
        return response;
    }


}
