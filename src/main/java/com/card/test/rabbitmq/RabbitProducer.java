package com.card.test.rabbitmq;

import com.card.test.ThreadUtil;
import com.rabbitmq.client.*;

/**
 * Created by cuihp on 2020/1/5.
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routing_key_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//默认PORT
    private static final String USER_NAME = "guest";//默认PORT


    public static void main(String[] args) throws Exception{
        System.out.println("producer start");
        // 创建连接
        Connection connection = RabbitConnectUtil.getConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 创建一个Type=DIRECT类型的，持久哈，非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);
        // 创建一个自动化的，非排他的，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        for (int i=0;i<100;i++){
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,("hello world"+i).getBytes());
            ThreadUtil.sleep(1000);
        }

        channel.close();
        connection.close();
        System.out.println("producer over");
    }



}
