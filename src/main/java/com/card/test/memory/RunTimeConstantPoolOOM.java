package com.card.test.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m：设置堆最大最小为20M，
 * -Xmn10m：设置年轻代10M
 * -XX:+PrintGCDetails 打印详细GC信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆溢出错误时生成堆转储文件.
 */
public class RunTimeConstantPoolOOM {


    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1 == str1.intern());

        String java = new StringBuilder("ja").append("va").toString();
        System.out.println(java == java.intern());

    }
}
