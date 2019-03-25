package com.card.controller;

import com.card.common.Response;
import com.card.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页控制器
 *
 * Created by hongpeng.cui on 2019/3/24.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = {" ", "/", "/dashboard"})
    @ResponseBody
    public String index() {
        return "dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String goLogin() {
        return "login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
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
