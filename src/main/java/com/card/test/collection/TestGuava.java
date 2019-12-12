package com.card.test.collection;

import com.google.common.collect.Range;

/**
 * Guava Test
 * Created by cuihp on 2019/11/11.
 */
public class TestGuava {


    public static void main(String[] args) {
        rangeTest();

    }

    private static void rangeTest() {
        Range range = Range.openClosed(1d,2d);
        System.out.println("range.isEmpty():"+range.isEmpty());
        System.out.println(" 2:"+range.contains(2d));
        System.out.println(" 2:"+range.contains(1d));
        System.out.println(" 2:"+range.contains(1.5));
        Range range1 = Range.closed(1,1);
        System.out.println("range1.isEmpty():"+range1.isEmpty());
    }


}
