package com.card.test.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Created by cuihp on 2020/9/14.
 */
public class IOTest {

    public static void main(String[] args) {
        nioTest();
    }

    private static void nioTest() {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("/Users/cuihp/Desktop/1.txt","rw");
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            int read = channel.read(allocate);
            while (read != -1) {
                allocate.flip();
                while (allocate.hasRemaining()) {
                    System.out.print((char) allocate.get());
                }
                allocate.compact();
                read = channel.read(allocate);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void oldIOTest() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("/Users/cuihp/Desktop/1.txt"));
            byte[] buf = new byte[1024];
            int read = in.read(buf);
            while (read != -1) {
                String s = new String(buf,0,read);
                System.out.print(s);
                read = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
