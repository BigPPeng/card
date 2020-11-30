package com.card.test.redis;

import com.test.lock.redis.RedisUtil;

/**
 * redis read
 *
 * Created by cuihp on 2019/11/20.
 */
public class Read {
    public static void main(String[] args) {
        Object a = RedisUtil.getInstance().get("A");
        System.out.println(a);
    }


}
