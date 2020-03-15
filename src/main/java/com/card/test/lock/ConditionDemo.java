package com.card.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cuihp on 2020/3/7.
 */
public class ConditionDemo {
    volatile int count = 1;
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();


    public static void main(String[] args) {
        Demo1();

    }

    private static void ShareDemo2() {
        ShareDemo shareDemo = new ShareDemo();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareDemo.print5();
            }
        },"t1").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareDemo.print10();
            }
        },"t2").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareDemo.print15();
            }
        },"t3").start();
    }

    private static void Demo1() {
        ConditionDemo demo = new ConditionDemo();
        new Thread(() ->{
            demo.lock.lock();
            try {
                while (demo.count < 10) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"\t 3");
                        demo.count++;
                        demo.conditionA.signal();
                        demo.conditionC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                demo.lock.unlock();
            }
        },"C").start();
        new Thread(() ->{
            demo.lock.lock();
            try {
                while (demo.count < 10) {
                    System.out.println(Thread.currentThread().getName()+"\t 2");
                    demo.conditionC.signal();
                    demo.conditionB.await();
                }
                demo.conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                demo.lock.unlock();
            }
        },"B").start();

        new Thread(() ->{
            demo.lock.lock();
            try {
                while (demo.count < 10) {
                    System.out.println(Thread.currentThread().getName()+"\t 1");
                    demo.conditionB.signal();
                    demo.conditionA.await();
                }
                demo.conditionB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                demo.lock.unlock();
            }
        },"A").start();


    }


}


class ShareDemo {
    private int number = 1;
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName()+ "\t print 5");
            this.number = 2;
            c2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName()+ "\t print 10");
            this.number = 3;
            c3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName()+ "\t print 15");
            this.number = 1;
            c1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}