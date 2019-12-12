package com.card.config;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;
import java.util.List;


@Configuration
@PropertySource("classpath:conf/custom/mongo.properties")
public class MongoConfig {

    @Value("${spring.data.mongodb.replSet}")
    private String replSet;
    @Value("${spring.data.mongodb.database}")
    private String database;




    @Bean
    public MongoTemplate getMongoTemplate() throws UnknownHostException {
        // 解析配置，获取机器地址，实例化Mongo对象
        List<ServerAddress> seeds = getServerAddressFromConfig(replSet);
        Mongo mongo = new Mongo(seeds);
        // 实例化MongoTemplate对象
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, database);
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
    private List<ServerAddress> getServerAddressFromConfig(String mongoDbReplSet) throws UnknownHostException {
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
