package com.card.test.lock;

import com.card.test.ThreadUtil;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by cuihp on 2020/3/6.
 */
public class SpinLockDemo {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {

            spinLockDemo.getLock();
            System.out.println(Thread.currentThread().getName() + " do start.....");
            ThreadUtil.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " do end.....");

            spinLockDemo.unLock();
        },"t1").start();



        new Thread(() -> {
            spinLockDemo.getLock();
            System.out.println(Thread.currentThread().getName() + " do start.....");
            ThreadUtil.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " do end.....");

            spinLockDemo.unLock();
        },"t2").start();


    }


    public void getLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " came in");
        while (!atomicReference.compareAndSet(null,thread)) {
            System.out.println(thread.getName() + " 自旋等待");
            ThreadUtil.sleep(100);
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " unLock");
        atomicReference.compareAndSet(thread,null);
    }




}
