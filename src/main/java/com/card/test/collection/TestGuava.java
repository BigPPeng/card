package com.card.test.collection;

import com.google.common.collect.Range;

/**
 * Guava Test
 * Created by cuihp on 2019/11/11.
 */
public class TestGuava {


    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.b.aa);
    }


    static class A {
        B b;

        public A() {
            b = new B(this);
        }
    }


    static class B {
        A a;
        int aa = 10;

        public B(A a) {
            this.a = a;
        }
    }


}
