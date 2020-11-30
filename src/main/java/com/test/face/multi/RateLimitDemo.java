package com.test.face.multi;

import com.card.test.ThreadUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.test.lock.redis.RedisUtil;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流器
 * Created by cuihp on 2020/11/25.
 */
public class RateLimitDemo {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        RateLimiter rateLimiter = RateLimiter.create(5);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                long start = System.currentTimeMillis();
                for (int j = 0; j < 10; j++) {
                    double acquire = rateLimiter.acquire();
                    System.out.println("线程："+Thread.currentThread().getName()+" 当前时间："+System.currentTimeMillis()+" 任务号："+j + " 等待时间："+acquire);
                }
                System.out.println("线程："+Thread.currentThread().getName() + " 耗时："+(System.currentTimeMillis() - start));
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("end");
    }






    private static void redisRateLimitTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] count = {0,0,0,0,0,0,0,0,0,0,0};
        int threadCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (final int[] i = {0}; i[0] < threadCount; i[0]++) {
            int i1 = i[0];
            new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    boolean require = RateLimitLock.getLimitLock().require();
                    if (require) {
                        System.out.println(Thread.currentThread().getName()+":done");
                        count[i1]++;
                    } else {
                        System.out.println(Thread.currentThread().getName()+":访问过快");
                    }
                    ThreadUtil.sleep(ThreadUtil.random() * 100);
                }
                countDownLatch.countDown();
            },"Thread"+ i[0]).start();
        }
        countDownLatch.await();
        System.out.println((System.currentTimeMillis() - start) + "MS");
        System.out.println(Arrays.toString(count));
    }

}



class RateLimitLock {
    private static final String LOCK_COUNT = "Distributed_Count";
    private static final String LOCK_TIME = "Distributed_Time";
    private static final int TIME_UNIT = 60 * 1000;
    private static final int COUNT = 10;


    private static RateLimitLock limitLock = new RateLimitLock();

    public static RateLimitLock getLimitLock() {
        return limitLock;
    }

    private RateLimitLock(){
    }


    public synchronized boolean require() {
        RedisUtil instance = RedisUtil.getInstance();
        boolean hasKey = instance.hasKey(LOCK_TIME);
        System.out.println(Thread.currentThread().getName()+"lock hasKey ："+hasKey);
        if (hasKey) {
            return instance.incr(LOCK_COUNT, (long) 1) <= COUNT;
        } else {
            instance.set(LOCK_TIME, System.currentTimeMillis() + "", 1);
            instance.set(LOCK_COUNT, 1 + "", 1);
            System.out.println("lock ++");
            return true;
        }
    }

    public synchronized void clear() {
        RedisUtil instance = RedisUtil.getInstance();
        instance.del(LOCK_TIME,LOCK_COUNT);
    }

    public synchronized int getCount() {
        RedisUtil instance = RedisUtil.getInstance();
        return (int)instance.get(LOCK_COUNT);
    }

}


