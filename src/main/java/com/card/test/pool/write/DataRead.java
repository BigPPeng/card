package com.card.test.pool.write;

import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 数据读取
 * Created by cuihp on 2019/11/18.
 */
public class DataRead {
    private static final String BASE_FILE = "/Users/cuihp/Desktop/temp/test/test";
    private static final String BASE_BOOK_FILE_NAME = "book";
    private static final String BASE_PRICE_FILE_NAME = "price";


    public static void main(String[] args) throws IOException {
        File base = new File(BASE_FILE);
        if (base.isFile()) {
            System.out.println(base.getName());
        } else {
            File[] files = base.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    FileReader fileReader = new FileReader(file);

                    char[] content = new char[10];
                    long length = file.length();
                    System.out.println(length);

                    int read = fileReader.read(content);
                    for (char c : content) {
                        System.out.print(c);
                    }
                    System.out.println();
                    System.out.println(read);
                    System.out.println();
                    fileReader.close();
                }
            }
        }



    }


}
