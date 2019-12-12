package com.card.test.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * java 将电脑中的文件与文件夹封装成File类。并对其进行操作
 *
 * 与系统无关：file:文件 directory:文件夹 path:路径
 */
public class FileDemo {
    public static String base = "/Users/cuihp/Desktop/temp";


    public static void main(String[] args) throws IOException {
        // demo1();
        // demo2();


    }

    private static void demo2() throws IOException {
        File create = new File(base+"/1","2");
        if (create.exists()) {
            System.out.println(create.getName());
            System.out.println(create.getParent());
            System.out.println(create.getPath());
            System.out.println(create.getAbsolutePath());
        } else {
//            System.out.println("Create:"+create.createNewFile());// 建立文件
            System.out.println("Create:"+create.mkdir());// 建立文件夹，父目录需存下，否则失败
            System.out.println("Create:"+create.mkdirs());// 建立文件夹，父目录不存在，则创建
        }
    }

    private static void demo1() {
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);
        File temp = new File(base);

        System.out.println("文件夹："+temp.isDirectory());
        System.out.println("文件："+temp.isFile());

        if (temp.isDirectory()) {
            System.out.println(Arrays.toString(temp.list()));
            System.out.println(Arrays.toString(temp.listFiles()));
        }

        File[] files = temp.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
            if ("20190802".equals(file.getName())) {
                File[] files1 = file.listFiles();
                System.out.println(Arrays.toString(files1));
                if (files1 == null) {
                    continue;
                }
                for (File file1 : files1) {
                    if (file1.isFile()) {
                        System.out.println("FileName:"+file1.getName()+" del res:"+file1.delete());
                    }
                }

            }
        }
    }


}
