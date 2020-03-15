package com.card.test.rabbitmq;

import com.card.test.ThreadUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by cuihp on 2020/1/5.
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//默认PORT
    private static final String USER_NAME = "guest";//默认PORT


    public static void main(String[] args) throws Exception{
        System.out.println("Consumer start");
//        Address[] addresses = new Address[]{new Address(IP_ADDRESS,PORT)};
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setUsername(USER_NAME);
//        connectionFactory.setPassword(USER_NAME);
        Connection connection = RabbitConnectUtil.getConnection();

        final Channel channel = connection.createChannel();
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("message:"+new String(body));
                ThreadUtil.sleep(1000);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,consumer);
        ThreadUtil.sleep(5000);
        channel.close();
        connection.close();
        System.out.println("Consumer over");
    }
}
