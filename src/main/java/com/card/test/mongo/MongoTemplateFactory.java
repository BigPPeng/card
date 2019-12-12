package com.card.test.mongo;


import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;
import java.util.List;

public class MongoTemplateFactory {

    private static final Object lock = new Object();
    private static MongoTemplate instance;

    public static MongoTemplate getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    try {
                        instance = getMongoTemplate();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }


    private MongoTemplateFactory(){

    }


    public static MongoTemplate getMongoTemplate() throws UnknownHostException {
        // 解析配置，获取机器地址，实例化Mongo对象
        List<ServerAddress> seeds = getServerAddressFromConfig("192.168.137.133:27017,192.168.137.132:27017");
        Mongo mongo = new Mongo(seeds);
        // 实例化MongoTemplate对象
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, "mongo_test");
        /* 设置mongoDB读偏好，本系统选择：primaryPreferred
         *
         * primary 主节点，默认模式，读操作只在主节点，如果主节点不可用，报错或者抛出异常。
         * primaryPreferred 首选主节点，大多情况下读操作在主节点，如果主节点不可用，如故障转移，读操作在从节点。
         * secondary 从节点，读操作只在从节点， 如果从节点不可用，报错或者抛出异常。
         * secondaryPreferred 首选从节点，大多情况下读操作在从节点，特殊情况（如单主节点架构）读操作在主节点。
         * nearest 最邻近节点，读操作在最邻近的成员，可能是主节点或者从节点
         */
        mongoTemplate.setReadPreference(com.mongodb.ReadPreference.primaryPreferred());
        return mongoTemplate;
    }

    /**
     * 字符串解析并生成ServerAddress对象
     *
     * @param mongoDbReplSet mongoDB的配置地址集合：10.50.8.60:27017,10.50.8.59:27017,10.50.8.58:27017
     * @return 地址解析后的ServerAddress对象集合
     * @throws UnknownHostException 错误地址异常
     */
    private static List<ServerAddress> getServerAddressFromConfig(String mongoDbReplSet) throws UnknownHostException {
        List<ServerAddress> seeds = Lists.newArrayList();
        String[] mongoDbServerAddressArray = mongoDbReplSet.split(",");
        for (String mongoDbServerAddress : mongoDbServerAddressArray) {
            String[] itemArray = mongoDbServerAddress.split(":");
            String host = itemArray[0];
            Integer port = Integer.valueOf(itemArray[1]);
            ServerAddress serverAddress = new ServerAddress(host, port);
            seeds.add(serverAddress);
        }
        return seeds;
    }

}
