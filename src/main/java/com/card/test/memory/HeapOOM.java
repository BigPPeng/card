package com.card.test.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆溢出
 *
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m：设置堆最大最小为20M，
 * -Xmn10m：设置年轻代10M
 * -XX:+PrintGCDetails 打印详细GC信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆溢出错误时生成堆转储文件
 * Created by cuihp on 2019/11/2.
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }


}
