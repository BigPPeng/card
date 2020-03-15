package com.card.test.memory;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 一直在GC，但是回收效果不好，每次回收不到2%，报这个异常
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 *
 * 定义：GC回收时间过长时抛出以上问题，超过98%的时间在GC，回收了不到2%的堆内存。迫使不断的GC，恶性循环，CPU100%。
 *
 * Created by cuihp on 2020/3/15.
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = Lists.newArrayList();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }

    }
}
