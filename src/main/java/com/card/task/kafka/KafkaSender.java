package com.card.task.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by hongpeng.cui on 2019/6/14.
 */
@Component
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    private final static String HELLO_KAFKA =  "Hello";

    @Resource
    private KafkaTemplate kafkaTemplate;


    public void sendTest() {

        try {
            String data = "hello kafka "+ System.currentTimeMillis();
            kafkaTemplate.send(HELLO_KAFKA,data);
        } catch (Exception e) {
            logger.error("发送信息异常了！");
        }

    }


}
