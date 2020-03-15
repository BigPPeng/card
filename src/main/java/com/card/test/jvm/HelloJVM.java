package com.card.test.jvm;

import java.lang.ref.Reference;

/**
 * Created by cuihp on 2020/3/13.
 */
public class HelloJVM {
    public static void main(String[] args) {
        printRunTime();





    }

    private static void printRunTime() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Total Memory(-Xms):"+totalMemory+"\t"+(totalMemory/1024d/1024d)+"MB");
        System.out.println("Max Memory(-Xmx):"+maxMemory+"\t"+(maxMemory/1024d/1024d)+"MB");
    }
}
