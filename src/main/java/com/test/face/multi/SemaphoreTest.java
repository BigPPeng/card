package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.util.concurrent.Semaphore;

/**
 * Created by cuihp on 2020/11/16.
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);


        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ThreadUtil.sleep(2000);

                System.out.println(Thread.currentThread().getName() + " do work");

                semaphore.release();

            },"T"+i).start();
        }

        System.out.println(semaphore.hasQueuedThreads());
        while (semaphore.hasQueuedThreads()) {
            System.out.println("可用："+semaphore.availablePermits()
            + " 正在等待:"+ semaphore.getQueueLength());
            ThreadUtil.sleep(100);
        }




    }


}
