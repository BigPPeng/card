package com.card.test.collection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cuihp on 2020/3/7.
 *
 * 一个初始变量为0，两个线程对其交替操作，一个加1一个减1。
 *
 * 1、   线程     操作       资源类
 * 2、   判断     干活       通知
 *
 */
public class ProdConsumerDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i <10; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

        new Thread(() -> {
            for (int i = 0; i <20; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        new Thread(() -> {
            for (int i = 0; i <10; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t3").start();

    }

}


class ShareData{
    private int number = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition consume = lock.newCondition();
    private Condition produce = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                produce.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t 生产 \t" + number);
            consume.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number != 1) {
                consume.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t 消费 \t" + number);
            produce.signalAll();
        } finally {
            lock.unlock();
        }
    }



}

