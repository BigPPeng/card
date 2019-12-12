package com.card.test.gc;

/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m：设置堆最大最小为20M，
 * -Xmn10m：设置年轻代10M
 * -XX:PermSize=10M -XX:MaxPermSize=10M ：永久代 最大10M
 * PSYoungGen 年轻代
 * ParOldGen 老年代
 * PSPermGen 永久代
 *
 * Created by cuihp on 2019/11/2.
 */
public class TestGC {
    private TestGC instance = null;
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        TestGC a = new TestGC();
        TestGC b = new TestGC();
        a.instance = b;
        b.instance = a;
        a = null;
        b = null;

        System.gc();
    }

}
