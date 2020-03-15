package com.card.test.memory;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2020/3/15.
 */
public class UnableCreateThread {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            int finalI = i;
            System.out.println("i:"+ finalI);
            new Thread(()-> {
                ThreadUtil.sleep(Integer.MAX_VALUE);
            }).start();
        }
    }
}
