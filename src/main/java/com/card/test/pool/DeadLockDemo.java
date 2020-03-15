package com.card.test.pool;

import com.card.test.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cuihp on 2020/3/8.
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "A";
        String lockB = "B";


        new Thread(() -> {
            synchronized (lockA) {
                System.out.println("持有A，等待B");
                ThreadUtil.sleep(1000);
                synchronized (lockB) {
                    System.out.println("持有A，持有B");
                }
            }
        },"t1").start();


        new Thread(() -> {
            synchronized (lockB) {
                System.out.println("持有B，等待A");
                ThreadUtil.sleep(1000);
                synchronized (lockA) {
                    System.out.println("持有B，持有A");
                }
            }
        },"t2").start();


    }

}
