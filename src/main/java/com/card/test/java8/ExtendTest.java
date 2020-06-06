package com.card.test.java8;

/**
 * Created by cuihp on 2020/5/24.
 */
public class ExtendTest {
    /*
     * 接口中提供默认方法实现产生的继承冲突问题的解决case
     * 1、类中实现的方法的优先级最高，类或者分类中的实现具有最高优先级。高于任何声明为默认方法的优先级。
     * 2、如果无法依据第一条判断，那么子接口的优先级最高，函数签名相同时，优先选择拥有最具体实现的默认方法的接口，
     *      如果B继承了A，则B比A更加具体。
     * 3、最后还是无法判断，则继承多个接口的类必须提供实现。
     */


    public static void main(String[] args) {
        //case 1 BB 继承 AA，所以比AA更具体，CC中调用的是BB中的实现。
//        CC cc = new CC();
//        cc.hello();
        // case 2 DD实现AA，但是没有重写hello() ，CC继承DD，实现AA，BB，调用hello方法实现的认识BB的实现
        CC cc = new CC();
        cc.hello();
        // case 3 , 继续case2 如果DD实现了hello()，则CC中调用hello()，则是DD实现。
        // case 4 , 继续case2 如果DD声明了一个抽象方法hello()，则CC必须实现hello()
        // case 5 , 变种case2，如果BB么有继承AA，CC实现了AA，BB存在菱形基础，必须实现hello()。

    }
}

interface AA {
    default void hello() {
        System.out.println("AA said hello!");
    }
}

interface BB extends AA{
    default void hello() {
        System.out.println("BB said hello!");
    }
}

class DD implements AA{

}

// case 1
//class CC implements AA,BB {
//
//}

class CC extends DD implements AA,BB {

}