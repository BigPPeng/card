package com.card.test.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cuihp on 2020/3/6.
 */
public class CountDownLatchDemo {



    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ "\t上完自习");
                countDownLatch.countDown();
            },CountryEnum.getById(i).name).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t-------关门");
    }

    /**
     * CountDownLatch 构造器必传一个count
     * 主要有两个方法，
     * 一个是wait(),调用的线程会阻塞在此处，知道CountDownLatch 中count减为1
     * 一个是countDown() 将CountDownLatch count数减1
     */
    private static void closeDoor() throws InterruptedException {



        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ "\t上完自习");
                countDownLatch.countDown();
            },"t"+i).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t-------关门");
    }

}
