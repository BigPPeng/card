package com.test.kafka;

import com.card.test.ThreadUtil;
import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.List;
import java.util.Properties;

import static com.test.kafka.KafkaDemoUtils.SERVER;

/**
 * Created by cuihp on 2020/12/5.
 */
public class ConsumerDemo {

    private static final String GROUP_NAME_HEAD = "group_";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_NAME_HEAD+"1");

        Consumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Lists.newArrayList(KafkaDemoUtils.TOPIC_NAME));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("key:" + record.key() + "  value:" + record.value() + " offset:" + record.offset());
                    ThreadUtil.sleep(100);
                }
            }
        } finally {
            consumer.close();
        }



    }
}
