package com.card.test;

import java.util.Random;

/**
 * Created by cuihp on 2020/1/3.
 */
public class ThreadUtil {

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static int random() {
        Random random = new Random();
        return random.nextInt(5);
    }
}
