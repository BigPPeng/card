package com.card.test.memory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cuihp on 2020/3/4.
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b1 = atomicInteger.compareAndSet(5, 6);
        System.out.println(atomicInteger.get() + " " + b1);
        boolean b = atomicInteger.compareAndSet(5, 7);
        System.out.println(atomicInteger.get() + " " + b);
    }

}
