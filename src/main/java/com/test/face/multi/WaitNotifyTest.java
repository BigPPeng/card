package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihp on 2020/11/12.
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        Object lock_write = new Object();
        Object lock_read = new Object();
        Thread threadAdd = new Thread(new Producer(lock_read,lock_write));
        threadAdd.setName("Add");
        Thread threadGet = new Thread(new ConSummer(lock_read,lock_write));
        threadGet.setName("Get");
        Thread threadGet1 = new Thread(new ConSummer(lock_read,lock_write));
        threadGet1.setName("Get2");

        threadAdd.start();
        threadGet.start();
        threadGet1.start();

        ThreadUtil.sleep(100000);
        Flag.flag = false;
        threadAdd.interrupt();
        threadGet.interrupt();
        threadGet1.interrupt();
        System.out.println("END");
    }
}

class Data {
    public static String a = "";
}

class Flag {
    public static boolean flag = true;
}

class Producer implements Runnable{
    private final Object lock_Read;
    private final Object lock_write;


    public Producer(Object lock_Read,Object lock_write) {
        this.lock_Read = lock_Read;
        this.lock_write = lock_write;
    }

    @Override
    public void run() {
        try {
            while (Flag.flag) {
                synchronized (lock_write) {
                    if (!Data.a.equals("")) {
                        lock_write.wait();
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    Data.a = currentTimeMillis + "---" + format.format(currentTimeMillis);
                    System.out.println(Thread.currentThread() + " add " + Data.a);
                    ThreadUtil.sleep(500);
                }
                synchronized (lock_Read) {
                    lock_Read.notify();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class ConSummer implements Runnable{
    private final Object lock_Read;
    private final Object lock_write;


    public ConSummer(Object lock_Read,Object lock_write) {
        this.lock_Read = lock_Read;
        this.lock_write = lock_write;
    }

    @Override
    public void run() {
        try {
            while (Flag.flag) {
                synchronized (lock_Read) {
                    if (Data.a.equals("")) {
                        lock_Read.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + " get " + Data.a);
                }
                synchronized (lock_write) {
                    Data.a = "";
                    lock_write.notify();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}