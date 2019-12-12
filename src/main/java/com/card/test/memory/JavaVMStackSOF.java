package com.card.test.memory;

/**
 * Java栈空间溢出
 *
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -Xss128k -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m：设置堆最大最小为20M，
 * -Xmn10m：设置年轻代10M
 * -Xss128k：设置栈容量 128K
 * -XX:+PrintGCDetails 打印详细GC信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆溢出错误时生成堆转储文件
 * Created by cuihp on 2019/11/2.
 * Created by cuihp on 2019/11/2.
 */
public class JavaVMStackSOF {

    private int stackLength = 1;
    public void stackLeak() {
        stackLength ++ ;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println(javaVMStackSOF.stackLength);
            throw e;
        }

    }
}
