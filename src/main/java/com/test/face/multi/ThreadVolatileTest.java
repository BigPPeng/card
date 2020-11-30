package com.test.face.multi;

/**
 * Created by cuihp on 2020/11/11.
 */
public class ThreadVolatileTest {

    /**
     * 1、Volatile主要保证变量在多个线程中可见，强制线程从公共堆栈取值，不从自己的私有堆栈取值。
     * 2、JVM内存模型，线程工作内存与主内存。
     *
     *
     * 1、volatile是轻量级的同步，保证可见性，synchronized是重量级锁，保证线程顺序性。
     * 2、volatile不会导致阻塞，synchronized阻塞。
     * 3、volatile修饰变量，synchronized修饰方法，代码块。
     *
     */

    public static void main(String[] args) {

    }
}
