package com.card.test.memory;

import com.card.test.ThreadUtil;
import com.google.common.collect.Lists;
import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m -XX:+PrintFlagsFinal
 *
 * NIO使用对外内存，堆内仅存储一个引用对象。
 *
 * java.lang.OutOfMemoryError: Direct buffer memory
 * Java使用的堆外直接内存满了
 *
 * Created by cuihp on 2020/3/15.
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        double v = VM.maxDirectMemory() / 1014d / 1024d;
        System.out.println("配置的maxDirectMemory："+v+"MB");


        List<ByteBuffer> byteBuffers = Lists.newArrayList();
        while (true){
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2 * 1024 * 1024);
            byteBuffers.add(byteBuffer);
        }
    }
}
