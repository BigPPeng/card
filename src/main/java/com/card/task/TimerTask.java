package com.card.task;

import com.card.task.kafka.KafkaConsume;
import com.card.task.kafka.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by hongpeng.cui on 2019/6/13.
 */
@Component
public class TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);


    @Resource
    private KafkaSender kafkaSender;

    @Resource
    private KafkaConsume kafkaConsume;

    public void doTimeWork(){
        Thread a = new Thread(new SendThread());
        a.setName("send");
        a.start();

        Thread b = new Thread(new Consume());
        b.setName("consume");
        b.start();
    }


    class SendThread implements Runnable {
        int i = 0;
        Random random = new Random();

        @Override
        public void run() {
            while (i < 100000) {
                int i = random.nextInt();
                kafkaSender.send(String.valueOf(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Consume implements Runnable {
        @Override
        public void run() {
            while (true) {
                kafkaConsume.consume();
            }
        }
    }


}
