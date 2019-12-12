package com.card.task;

import com.card.task.kafka.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by hongpeng.cui on 2019/6/13.
 */
@Service
@Configuration
@EnableScheduling
public class TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);


    @Resource
    private KafkaSender kafkaSender;


//    @Scheduled(cron = "05/5 * * * * ?")
    public void doTimeWork(){

        Calendar calendar = Calendar.getInstance();
        logger.info("时：{},分：{},秒：{}",calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));

//        kafkaSender.sendTest();
    }


}
