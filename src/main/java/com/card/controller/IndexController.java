package com.card.controller;

import com.card.common.Response;
import com.card.model.User;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主页控制器
 *
 * Created by hongpeng.cui on 2019/3/24.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = {" ", "/", "/dashboard"})
    public String index() {
        return "dashboard";
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        CaptchaUtil.out(gifCaptcha,request,response);
        String code = gifCaptcha.text().toLowerCase();
        logger.info("code:{}",code);
        request.getSession().setAttribute("CAPTCHA",code);
    }


    @GetMapping("/captcha/test")
    @ResponseBody
    public String captchaTest(HttpServletRequest request,String code) throws IOException {
        String captcha = (String)request.getSession().getAttribute("CAPTCHA");
        logger.info("session:{},param:{}",captcha,code);
        return captcha.equalsIgnoreCase(code) + "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "login";
    }

    @PostMapping(value = "/login/in")
    public String login(String username,String password,String code,HttpServletRequest request) throws IOException {
        logger.info("name:{},pass:{},code:{}",username,password,code);
        String captcha = (String)request.getSession().getAttribute("CAPTCHA");
        if (captcha == null) {
            return "login";
        }
        logger.info("session:{},param:{}",captcha,code);
        if (captcha.equalsIgnoreCase(code)) {
            return "dashboard";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Response check(){
        logger.info("/check");
        Response<User> response = new Response<>();

        User user = new User();
        user.setId(2);
        user.setName("hongpeng.cui");
        response.setData(user);
        response.setStatus(1);
        response.setMessage("SUCCESS");
        return response;
    }




}
