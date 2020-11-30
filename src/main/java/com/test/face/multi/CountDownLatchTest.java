package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cuihp on 2020/11/16.
 */
public class CountDownLatchTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        /*
         * CountDownLatch允许一个或者多个线程等待其他线程完成后再继续进行执行。
         * 实现线程间的交互
         */
        countDownLatchTest();
        testJoin();

    }

    private static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(()->{
            ThreadUtil.sleep(1000);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName()+" end");
        },"T1");
        Thread t2 = new Thread(()->{
            ThreadUtil.sleep(2000);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName()+" end");
        },"T2");

        t1.start();
        t2.start();


        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+" end");
    }

    /**
     * join() 实现线程等待
     * @throws InterruptedException
     */
    private static void testJoin() throws InterruptedException {
        Thread t1 = new Thread(()->{
            ThreadUtil.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" end");
        },"T1");
        Thread t2 = new Thread(()->{
            ThreadUtil.sleep(2000);
            System.out.println(Thread.currentThread().getName()+" end");
        },"T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName()+" end");
    }


}
