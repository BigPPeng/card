package com.test.lock.redis;

import redis.clients.jedis.Jedis;

/**
 * redis实现分布式锁
 *
 * Created by cuihp on 2020/8/14.
 */
public class TestRedisLock {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        jedis.set("run_test_redis","www.cuihongpeng.test");
        String run_test_redis = jedis.get("run_test_redis");
        System.out.println("key:run_test_redis value:"+run_test_redis);


    }



}
