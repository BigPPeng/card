package com.card.test.pool;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2020/11/9.
 */
public class ThreadLocalTest {

    static ThreadLocal<Integer> local = new ThreadLocal<>();

    static Integer number = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                local.set(10);
                local.set(local.get() + 10);
                System.out.println(Thread.currentThread().getName() + " local : " +local.get());

                number += 10;
                System.out.println(Thread.currentThread().getName() + " number : " +number);

            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
        System.out.println(Thread.currentThread().getName() + " : " +number);
        System.out.println(Thread.currentThread().getName() + " local : " +ThreadLocalTest.local.get());
    }


}
