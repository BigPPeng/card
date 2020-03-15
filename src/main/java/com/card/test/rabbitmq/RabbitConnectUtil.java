package com.card.test.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cuihp on 2020/1/6.
 */
public class RabbitConnectUtil {
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//默认PORT
    private static final String USER_NAME = "guest";//默认PORT

    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(USER_NAME);
        // 创建连接
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            System.out.println("get connection IOException");
            throw e;
        } catch (TimeoutException e) {
            System.out.println("get connection TimeoutException");
            throw e;
        }
        return connection;
    }
}
