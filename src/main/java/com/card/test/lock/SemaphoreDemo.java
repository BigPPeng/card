package com.card.test.lock;

import com.card.test.ThreadUtil;

import java.util.concurrent.Semaphore;

/**
 * Created by cuihp on 2020/3/6.
 */
public class SemaphoreDemo {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t获取桌子了，开始吃火锅");
                    ThreadUtil.sleep((finalI + 1) % 3 * 1000 * 2);
                    System.out.println(Thread.currentThread().getName() + "\t结束吃火锅");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}
