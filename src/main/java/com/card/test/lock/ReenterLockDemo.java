package com.card.test.lock;

import com.card.test.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cuihp on 2020/3/6.
 */
public class ReenterLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();
//        new Thread(() -> {
//            try {
//                phone.sendMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t1").start();
//
//        new Thread(() -> {
//            try {
//                phone.sendMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t2").start();


        new Thread(() -> {
            try {
                phone.set();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t3").start();

        new Thread(() -> {
            try {
                phone.set();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t4").start();
    }




}




class Phone {

    Lock lock = new ReentrantLock();

    public void set() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " invoke set()");
            get();
        } finally {

            lock.unlock();
        }
    }


    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " invoke get()");
        } finally {
            lock.unlock();
        }
    }


    public synchronized void sendMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + " invoked sendMS()");
        ThreadUtil.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " wait");
        sendEmail();
    }


    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + " invoked sendEmail()");

    }

}

