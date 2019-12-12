package com.card.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void serviceA() {
        lock.lock();
        System.out.println("test service A condition A await");
        try {
            conditionA.await();
            System.out.println("test service A condition A open");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void serviceAA() {
        lock.lock();
        System.out.println("test serviceAA condition A await");
        try {
            conditionA.await();
            System.out.println("test serviceAA condition A open");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void serviceB() {
        lock.lock();
        System.out.println("test service B condition B await");
        try {
            conditionB.await();
            System.out.println("test service B condition B open");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalA() {
        lock.lock();
        System.out.println("signalA");
        conditionA.signalAll();
        lock.unlock();
    }

    public void signalB() {
        lock.lock();
        System.out.println("signalB");
        conditionB.signalAll();
        lock.unlock();
    }

}
