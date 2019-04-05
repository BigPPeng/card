package com.card.controller;

import com.alibaba.fastjson.JSON;
import com.card.common.CardUtil;
import com.card.common.JavaMail;
import com.card.common.Response;
import com.card.model.User;
import com.card.model.enums.UserStatus;
import com.card.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 登录注册模块
 * <p>
 * Created by hongpeng.cui on 2019/3/26.
 */
@Controller
@RequestMapping(value = "/LoginController")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Resource
    private UserService userService;

    /**
     * 返回值为6位数字验证码，验证可以在前端做，也可以在后台
     *
     * @param email 邮箱地址 or 手机号
     * @return 6位验证码
     */
    @RequestMapping(value = "/getCheckCode")
    @ResponseBody
    public Response<String> getEmailCheckCode(String email, HttpServletRequest request) {
        logger.info("getEmailCheckCode: email:{}",email);
        Response<String> response = new Response<>();
        //邮箱信息判断
        if (email == null || "".equals(email) || !CardUtil.isEmail(email)) {
            response.setStatus(1);
            response.setMessage("邮箱有空或者不合法");
            return response;
        }
        String code = JavaMail.getIdentifyingCode();
        logger.info("getEmailCheckCode: code:{}",code);
        // 发送邮件
        JavaMail.sendEmail(code, email);
        String key = "code"+email;
        logger.info("getEmailCheckCode: session key:{}",key);
        request.getSession().setAttribute(key, code);
        response.setMessage(code);
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
    @ResponseBody
    public Response<Boolean> verifyEmailCheckCode(String email, String code, HttpServletRequest request) {
        logger.info("getEmailCheckCode: email:{},code:{}",email,code);
        Response<Boolean> response = new Response<>();
        String key = "code"+email;
        String sessionCode = (String) request.getSession().getAttribute(key);
        if (sessionCode == null || sessionCode.isEmpty()) {
            response.setMessage("验证码失效");
            response.setStatus(1);
            response.setData(Boolean.FALSE);
            return response;
        }
        logger.info("getEmailCheckCode:session key:{},code:{}",key,sessionCode);
        if (code != null && !code.isEmpty() && sessionCode.equals(code)) {
            response.setMessage("验证成功");
            response.setData(Boolean.TRUE);
            return response;
        }
        request.getSession().removeAttribute(key);
        response.setMessage("验证失败");
        response.setStatus(1);
        response.setData(Boolean.FALSE);
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
    @ResponseBody
    public Response<Boolean> verifyUserInfoBeforeRes(String userName, String email, String phoneNumber) {
        logger.info("verifyUserInfoBeforeRes userName:{},email:{},phone:{}",userName,email,phoneNumber);
        Response<Boolean> response = new Response<>();
        Map<String,Object> param = Maps.newHashMap();
        if (!Strings.isNullOrEmpty(userName)) {
            param.put("name",userName);
        }
        if (!Strings.isNullOrEmpty(email) && CardUtil.isEmail(email)) {
            param.put("userEmail",email);
        }
        if (!Strings.isNullOrEmpty(phoneNumber) && CardUtil.isPhoneNumber(phoneNumber)) {
            param.put("phoneNumber",phoneNumber);
        }

        Response<Integer> response1 = userService.getCount(param);
        logger.info("verifyUserInfoBeforeRes response1:{}",JSON.toJSONString(response1));
        if (response1.getStatus() != 0 || response1.getData() != 0) {
            response.setStatus(response1.getStatus());
            response.setMessage("数据存在或者非法，请检查。"+response1.getMessage());
            return response;
        }

        response.setData(response1.getData() == 0);
        return response;
    }


    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public Response<Boolean> userRegister(String userName, String email, String phoneNumber, String password) {
        logger.info("verifyUserInfoBeforeRes userName:{},email:{},phone:{},pass:{}",userName,email,phoneNumber,password);
        Response<Boolean> response = new Response<>();
        User user = new User();
        user.setName(userName);
        user.setPass(password);
        user.setUserEmail(email);
        user.setStatus(UserStatus.DEFAULT.status);
        user.setIdentityCardNumber("");
        user.setHomeAddress("");
        user.setRealName("");
        user.setReceiveAddress("");

        user.setCreateTime(new Date());
        user.setLoginTime(new Date());
        user.setPhoneNumber(phoneNumber);
        Response<Long>  response1 = userService.insertUser(user);
        if (response1.getStatus() != 0) {
            response.setMessage(response1.getMessage());
            response.setStatus(response1.getStatus());
            return response;
        }
        response.setData(Boolean.TRUE);
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
    @ResponseBody
    public Response<Boolean> userLogin(String nameOrEmailOrPhone, String password, int type, HttpServletRequest request) {
        logger.info("verifyUserInfoBeforeRes nameOrEmailOrPhone:{},password:{},type:{}",nameOrEmailOrPhone,password,type);
        Response<Boolean> response = userService.userLogin(nameOrEmailOrPhone, password, type);
        if (response.getStatus() == 0) {
            Response<User> userResponse = userService.getUserInfo(nameOrEmailOrPhone,type);
            if (userResponse.getStatus() != 0) {
                response.setMessage("信息错误");
                response.setStatus(1);
                response.setData(Boolean.FALSE);
                return response;
            }
            request.getSession().setAttribute(userResponse.getData().sessionKey(),userResponse.getData());
        }
        return response;
    }

    @RequestMapping(value = "/checkLog")
    @ResponseBody
    public Response<Boolean> checkLog(String nameOrEmailOrPhoneOrId, int type, HttpServletRequest request) {
        Response<Boolean> response = new Response<>();
        Response<User> userResponse = userService.getUserInfo(nameOrEmailOrPhoneOrId,type);
        if (userResponse.getStatus() != 0) {
            response.setMessage("信息错误");
            response.setStatus(1);
            response.setData(Boolean.FALSE);
            return response;
        }
        User user = (User) request.getSession().getAttribute(userResponse.getData().sessionKey());
        response.setData(user != null);
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
    @ResponseBody
    Response<Boolean> userLogOut(String nameOrEmailOrPhone, int type) {
        Response<Boolean> response = new Response<>();

        return response;
    }


}
