package com.card.controller.annotation;

import com.card.task.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestKafkaController {
    private static final Logger logger = LoggerFactory.getLogger(TestKafkaController.class);



    @Resource
    private TimerTask task;


    @RequestMapping(value = "/testKafka")
    @ResponseBody
    public String testAnnotation(String message) {
        task.doTimeWork();
        return "done";
    }





}
