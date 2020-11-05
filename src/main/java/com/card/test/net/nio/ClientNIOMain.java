package com.card.test.net.nio;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihp on 2020/10/10.
 */
public class ClientNIOMain {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new TimeClientHandler("client:" + i, "", 9889, countDownLatch));
        }

        System.out.println("等待线程执行完毕");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务执行完毕，继续执行主线程，关闭池子");
        executorService.shutdownNow();
    }

}
