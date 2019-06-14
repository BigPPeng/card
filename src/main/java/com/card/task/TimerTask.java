package com.card.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by hongpeng.cui on 2019/6/13.
 */
@Service
@Configuration
@EnableScheduling
public class TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);


    @Scheduled(cron = "05/10 * * * * ?")
    public void doTimeWork(){

        Calendar calendar = Calendar.getInstance();
        logger.info("时：{},分：{},秒：{}",calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));

        logger.info("定时任务");

    }


}
