package com.card.task.kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
public class KafkaConsume {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsume.class);
//    @Value("${kafka.bootstrap-servers}")
//    private String server;
//    @Value("${kafka.topic}")
//    private String topic;

    private KafkaConsumer<String,String> consumer;

    public KafkaConsume() {
        initConsumer();
    }

    private void initConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");// key 编码器
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");// value 编码器
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");//发送失败重试次数
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Lists.newArrayList("test"));
    }


    public void consume() {
        ConsumerRecords<String, String> poll = consumer.poll(500);
        for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
            String value = stringStringConsumerRecord.value();
            logger.info("consume success value:{}",value);
        }
    }



}
