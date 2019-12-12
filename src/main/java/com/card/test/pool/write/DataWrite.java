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
public class DataWrite {
    private static final String BASE_FILE = "/Users/cuihp/Desktop/temp/test/";
    private static final String BASE_BOOK_FILE_NAME = "book";
    private static final String BASE_PRICE_FILE_NAME = "price";

    private static final String[] PUB = {"cui","bai","li","wang","jin","zhang","zhu"};
    private static final String[] NAME = {"编译原理","数据接口","网络","Java","数学","英语","艺龙"};
    private static final int[] ID = {1,2,3,4,5,6,7};
    private static final int[] WRITER_ID = {9,1,12,3,4,5,6};
    private static final long NOW = System.currentTimeMillis();
    private static final long ONE_DAY_MIL = 24 * 60 * 60 * 1000;

    public static void main(String[] args) {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random(currentTimeMillis);
        List<Book> books = Lists.newArrayListWithCapacity(150);
        List<Price> prices = Lists.newArrayListWithCapacity(1200);
        while (i < 1000000) {
            int i1 = random.nextInt(7);
            Book book = new Book();
            book.setName(NAME[i1]);
            book.setBookId(i);
            book.setPublishId(ID[i1]);
            book.setWriterId(WRITER_ID[i1]);
            book.setWriterName(PUB[i1]);
            books.add(book);


            int j = 0;
            while (j < i1) {
                Price price = new Price(i,NOW + ((i1 + 1) * ONE_DAY_MIL),i1,i1+ 100 );
                prices.add(price);
                j++;
            }

            if (books.size() > 100) {
                write(i,BASE_BOOK_FILE_NAME,JSON.toJSONString(books));
                books.clear();
            }
            if (prices.size() > 1000) {
                write(i,BASE_PRICE_FILE_NAME,JSON.toJSONString(prices));
                prices.clear();
            }
            i++;
        }
        System.out.println("time use : " + (System.currentTimeMillis() - currentTimeMillis));
    }


    private static void write(int i,String fileName, String content) {
        String full = BASE_FILE + fileName +"_" + i +".txt";
        File file1 = new File(BASE_FILE,fileName +"_" + i +".txt");

        try {
            FileWriter writer = new FileWriter(file1,true);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("name:"+full+" write error");
            e.printStackTrace();
        }

    }


}
