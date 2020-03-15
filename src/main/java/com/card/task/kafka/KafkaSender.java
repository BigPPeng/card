package com.card.task.kafka;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;


@Component
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
    private static Producer<String, String> kafkaProducer;
//    @Value("${kafka.bootstrap-servers}")
//    private String server;
//    @Value("${kafka.topic}")
//    private String topic;



    public KafkaSender() {
        kafkaProducer = createKafkaProducer();
    }

    private Producer<String, String> createKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");// key 编码器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");// value 编码器
        props.put("retries", 3);//发送失败重试次数
        props.put("acks", "all");
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        return new KafkaProducer<>(props);
    }


    public void send(final String value) {
        kafkaProducer.send(new ProducerRecord<String, String>("test", value), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    logger.debug("send success , value : {}",value);
                } else {
                    logger.error("send error , value : {}",value);
                }
            }
        });
    }


}
