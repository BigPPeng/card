package com.test.face.jvm;

/**
 * Created by cuihp on 2020/11/19.
 */
public class ClassLoaderTestDemo2 {

    public static void main(String[] args) {
        /*
        * A static
        * B static
        * A Con
        * B Con
        * */
//        B b = new B();

        /*
         * 仅打印10
         * 不会加载类A，类B
         */
//        System.out.println(B.BB);


        /*
         * A static
         * 10
         *
         * 不会加载类B
         */
//        System.out.println(B.AA);
    }

}

class A {

    public static int AA = 10;
    public final static int BB = 10;

    public A() {
        System.out.println("A Con");
    }

    static {
        System.out.println("A static");
    }
}

class B extends A {
    public B() {
        System.out.println("B Con");
    }

    static {
        System.out.println("B static");

    }


}