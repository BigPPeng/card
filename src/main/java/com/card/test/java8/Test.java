package com.card.test.java8;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * Created by cuihp on 2020/5/11.
 */
public class Test {
    public static void main(String[] args) {
//        List<Integer> a = Lists.newArrayList(3,45,1,35,53,34);
//        System.out.println(a);
//        a.sort(Integer::compareTo);
//        System.out.println(a);
//
//
//        List<A> b = Lists.newArrayList(new A(134,2),new A(2,3),new A(232,42));
//        System.out.println(b);
//        b.sort(Comparator.comparing(A::getA));
//        System.out.println(b);
//        b.sort(Comparator.comparing(A::getAA));
//        System.out.println(b);
//        b.sort(Comparator.comparing(A::getB));
//        System.out.println(b);
//
//        Thread aa = new Thread(() -> {
//            System.out.println("----");
//            System.out.println("a");
//        });
//        aa.start();

        int blackWeek = 48;
        for (int i = 0; i < 7; i++) {
            System.out.println(isSetBit(blackWeek, i));
        }


    }

    public static boolean isSetBit(int v, int n) {
        return (v & bitValue(n)) != 0;
    }

    private static int bitValue(int n) {
        return n >= 32 ? 0 : 1 << n;
    }
}

@FunctionalInterface
interface Run{
    void run();
}

class A{
    private int a;
    private int b;

    public A(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getAA() {
        return -a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}