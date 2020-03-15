package com.card.test.pool;

import java.util.concurrent.*;

/**
 * Created by cuihp on 2020/3/7.
 *
 * Array        Arrays
 * Collection   Collections
 * Executor     Executors
 *
 */

class MyReject implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("reject");
    }
}

public class PoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void demo2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                        2,
                        5,
                        2,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 10; j++) {
                        threadPoolExecutor.execute(() -> {
                            System.out.println(Thread.currentThread().getName()+"\t办理业务");
                        });
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private static void demo1() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);// 固定5个线程 适合执行时间长的任务
//        ExecutorService executorService = Executors.newSingleThreadExecutor();// 一池一个线程，使用一个任务一个任务执行的场景
//        ExecutorService executorService = Executors.newCachedThreadPool();// 一池N个线程 适合任务执行时间短的任务

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
    }
}
