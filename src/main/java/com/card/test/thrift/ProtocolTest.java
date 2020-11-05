package com.card.test.thrift;

import org.apache.thrift.TException;

import java.util.Arrays;

/**
 * Created by cuihp on 2020/9/28.
 */
public class ProtocolTest {
    public static void main(String[] args) {
        int i = 32;
        System.out.println(Integer.toBinaryString(i));
        byte[] bytes = writeI32(i);
        System.out.println(Arrays.toString(bytes));
    }



    public static byte[] writeI32(int i32){
        byte[] inoutTemp = new byte[8];
        inoutTemp[0] = (byte)(0xff & (i32 >> 24));
        inoutTemp[1] = (byte)(0xff & (i32 >> 16));
        inoutTemp[2] = (byte)(0xff & (i32 >> 8));
        inoutTemp[3] = (byte)(0xff & (i32));
        System.out.println(Integer.toBinaryString(i32));
        return inoutTemp;
    }
}
