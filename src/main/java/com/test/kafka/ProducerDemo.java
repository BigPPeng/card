package com.test.kafka;

import com.card.test.ThreadUtil;
import org.apache.kafka.clients.producer.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static com.test.kafka.KafkaDemoUtils.SERVER;
import static com.test.kafka.KafkaDemoUtils.TOPIC_NAME;

/**
 * Created by cuihp on 2020/12/5.
 */
public class ProducerDemo {


    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.RETRIES_CONFIG,3);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,323840);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,10);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,3000);


        Producer<String,String> producer = new KafkaProducer<String, String>(properties);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        for (int j = 0; j < 100; j++) {
            long currentTimeMillis = System.currentTimeMillis();

            String s = simpleDateFormat.format(new Date(currentTimeMillis));
            Future<RecordMetadata> send = producer.send(new ProducerRecord<>(TOPIC_NAME, s));
            System.out.println("now :" +s + " send :" +send.isDone());
            ThreadUtil.sleep(1000);
        }

        producer.close();

    }

}
