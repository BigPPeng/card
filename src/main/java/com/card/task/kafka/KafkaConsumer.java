package com.card.task.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created by hongpeng.cui on 2019/6/14.
 */
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


//    @KafkaListener(topics = {"Hello"})
    public void consumer(String message) {
        logger.info("data"+message);
    }


}
