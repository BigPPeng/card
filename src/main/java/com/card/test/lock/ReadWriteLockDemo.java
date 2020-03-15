package com.card.test.lock;

import com.card.test.ThreadUtil;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by cuihp on 2020/3/6.
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        Demo demo = new Demo(1,2,3);

        new Thread(() -> {
            demo.lock.readLock().lock();

            try {
                demo.toString();
            } finally {
                demo.lock.readLock().unlock();
            }
        },"t1").start();


        new Thread(() -> {
            demo.lock.readLock().lock();

            try {
                demo.toString();
            } finally {
                demo.lock.readLock().unlock();
            }
        },"t2").start();


        new Thread(() -> {
            demo.lock.readLock().lock();

            try {
                demo.toString();
            } finally {
                demo.lock.readLock().unlock();
            }
        },"t3").start();


        new Thread(() -> {
            demo.lock.writeLock().lock();

            try {
                demo.write();
            } finally {
                demo.lock.writeLock().unlock();
            }
        },"t4").start();


        new Thread(() -> {
            demo.lock.readLock().lock();

            try {
                demo.toString();
            } finally {
                demo.lock.readLock().unlock();
            }
        },"t5").start();




    }


}



class Demo {

    ReadWriteLock lock = new ReentrantReadWriteLock();

    int a;
    int b;
    int c;

    public Demo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public void write() {

        System.out.println(Thread.currentThread().getName()+ " write start");
        ThreadUtil.sleep(2000);
        this.a = 100;
        String s = "Demo{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
        System.out.println(Thread.currentThread().getName() + s);
        System.out.println(Thread.currentThread().getName()+ " write end");
    }

    @Override
    public String toString() {
        String s = "Demo{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
        System.out.println(Thread.currentThread().getName()+ " read start");
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + s);
        System.out.println(Thread.currentThread().getName()+ " read end");

        return s;

    }
}
