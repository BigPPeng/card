package com.card.test.java8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cuihp on 2020/5/12.
 */
public class Test3 {
    public static void main(String[] args) {

    }


    public static void setBitFlag(byte bitFlag, int index, boolean flag) {
        if (index > 7) {
            return;
        }
        byte i = (byte)(1 << index);
        if (flag) {
            bitFlag = (byte) (bitFlag | i);
        } else {
            bitFlag = (byte) (bitFlag & ~i);
        }
    }

}
