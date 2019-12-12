package com.card.test.pool.write;

import com.alibaba.fastjson.JSON;
import com.card.test.pool.model.Book;
import com.card.test.pool.model.Price;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * 数据存储
 * Created by cuihp on 2019/11/17.
 */
public class DataWrite2 {
    private static final String BASE_FILE = "/Users/cuihp/Desktop/temp/test/";
    private static final String BASE_BOOK_FILE_NAME = "book";
    private static final String BASE_PRICE_FILE_NAME = "price";

    private static final String[] PUB = {"cui","bai","li","wang","jin","zhang","zhu"};
    private static final String[] NAME = {"编译原理","数据接口","网络","Java","数学","英语","艺龙"};
    private static final int[] ID = {1,2,3,4,5,6,7};
    private static final int[] WRITER_ID = {9,1,12,3,4,5,6};
    private static final long NOW = System.currentTimeMillis();
    private static final long ONE_DAY_MIL = 24 * 60 * 60 * 1000;

    public static void main(String[] args) throws IOException {
        String s = "hello world 你好 世界 哈哈哈";
        byte[] bytes = s.getBytes();
        System.out.println(s);

        File file = new File("/Users/cuihp/Desktop/temp/testio/text.txt");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
////        outputStream.flush();
//        outputStream.close();
        FileInputStream inputStream = new FileInputStream(file);

        byte[] in = new byte[bytes.length];
        int read = inputStream.read(in);
        System.out.println(new String(in));
        System.out.println(read);
        System.out.println(bytes.length);
        System.out.println(inputStream.read());


    }




}
