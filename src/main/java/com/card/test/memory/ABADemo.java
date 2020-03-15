package com.card.test.memory;

import com.card.test.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by cuihp on 2020/3/5.
 */
public class ABADemo {

//    static AtomicReference<Integer> atomicInteger = new AtomicReference<>(100);


    /**
     * 此处有一个bug，
     *
     * 初始化的Integer值不能超过
     *
     */
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {


//        System.out.println("ABA问题产生");
//        new Thread(() -> {
//            atomicInteger.compareAndSet(100,101);
//            System.out.println(atomicInteger.get());
//            atomicInteger.compareAndSet(101,100);
//            System.out.println(atomicInteger.get());
//        },"t1").start();
//
//        new Thread(() -> {
//            System.out.println(atomicInteger.get());
//            ThreadUtil.sleep(1000);
//            atomicInteger.compareAndSet(100,2019);
//            System.out.println(atomicInteger.get());
//        },"t2").start();
//
//        ThreadUtil.sleep(2000);
//        System.out.println("ABA问题解决");



        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t3 此时版本："+ stamp);
            ThreadUtil.sleep(1000);
            boolean b = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("t3 结果："+b+" 此时版本："+ atomicStampedReference.getStamp());
            System.out.println("t3 " + atomicStampedReference.getReference());
            boolean b1 = atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println("t3 结果："+b1+" " + atomicStampedReference.getReference());
        },"t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t4 4此时版本："+ stamp);
            ThreadUtil.sleep(6000);
            boolean b = atomicStampedReference.compareAndSet(100, 1999, stamp, stamp + 1);
            System.out.println("t4 res:"+b + "版本"+atomicStampedReference.getStamp());

            System.out.println("t4 " + atomicStampedReference.getReference());
        },"t4").start();



    }






}


class Fun{
    String name;
    int age;

    public Fun(String name, int age) {
        this.name = name;
        this.age = age;
    }
}