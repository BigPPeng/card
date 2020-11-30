package com.test.face.multi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuihp on 2020/11/17.
 */
public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {
        /*
        * UnSafe类、CAS指令
        *
        * 可以看到原子性的实现没有用synchronized，说明是非阻塞同步。
        * 最核心的方法是compareAndSwapInt，也就是所谓的CAS操作。
        * CAS操作依赖底层硬件的CAS指令，CAS指令有两个步骤：冲突检测和更新操作，但是这两个步骤合起来成为一个原子性操作。
        * CAS指令需要3个操作数：内存位置（V），旧的预期值（A），新值（B）。
        * CAS指令执行时，首先比较内存位置V处的值和A的值是否相等（冲突检测），如果相等，就用新值B覆盖A（更新操作），
        * 否则，就什么也不做。所以，一般循环执行CAS操作，直到成功为止。
        *
        * ABA问题
        * 在进行CAS操作的时候，因为在更改V之前，CAS主要询问“V的值是否仍然为A”，所以在第一次读取V之后以及对V执行CAS操作之前，
        * 如果将值从A改为B，然后再改回A，会使基于CAS的算法混乱。在这种情况下，CAS操作会成功。这类问题称为ABA问题。
        * 加操作次数记录，配合使用。
        * */
        atomicIntegerTest();
    }

    /**
     * 3000个客户端
     * 40个服务线程
     * 服务次数计数
     */
    private static void atomicIntegerTest() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();

        final int[] count = {0};

        int client = 30000;

        CountDownLatch countDownLatch = new CountDownLatch(client);
        Semaphore semaphore = new Semaphore(50);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < client; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    atomicInteger.incrementAndGet();
                    count[0]++;
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(atomicInteger.get());
        System.out.println(count[0]);
    }


}
