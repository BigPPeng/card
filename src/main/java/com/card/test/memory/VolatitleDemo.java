package com.card.test.memory;

import com.card.test.ThreadUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cuihp on 2020/3/3.
 */
public class VolatitleDemo {
    public static void main(String[] args) {
        atomicNot();
    }

    /**
     * volatile不保证原子性示例
     *
     * 方法：1、同步Sy 2、使用Atomic...
     */
    private static void atomicNot() {
        MyData myData = new MyData();
        for (int i=0;i<20;i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }).start();
        }

        ThreadUtil.sleep(2000);


        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(myData.number);
        System.out.println(myData.atomicLong.get());
    }

    /**
     * volatile 保证可见性，一个线程修改，会直接刷会主内存，并通知其他线程
     */
    private static void seeOkOfVolatile() {
        MyData myData = new MyData();
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" come in");
            ThreadUtil.sleep(3000);
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " up number :" + myData.number);
        });
        aa.start();

        while (myData.number == 0) {
            ThreadUtil.sleep(1000);
            System.out.println("wait");
        }
        System.out.println(Thread.currentThread().getName() + "main over");
    }
}

class Singleton{
    private static volatile Singleton singleton = null;

    private Singleton() {

    }

    /**
     * 如果singleton没有用volatile修饰，在高并发环境下由于指令重拍可能存在问题，一个线程在new对象时，没有完成时。就被其他线程拿走用了。
     * 简化：new Singleton（）
     *      1、分配空间
     *      2、初始化
     *      3、引用指向空间 此时 instance != null
     *
     *      2，3步之间没有数据依赖，可以重排。顺序变为 1，3，2
     *      在第三步后singleton != null。其他线程拿走使用发生错误。概率极小。
     *
     * 使用volatile禁止指令重排
     *
     * @return Singleton
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}



class MyData{
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }


    public void addPlusPlus() {
        number++;
    }


    AtomicLong atomicLong = new AtomicLong();
    public void addAtomic() {
        atomicLong.getAndIncrement();
    }

}