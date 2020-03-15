package com.card.test.jvm;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2020/3/11.
 */
public class HelloGC {

    public static void main(String[] args) {
        System.out.println("-----hello gc");
        ThreadUtil.sleep(Long.MAX_VALUE);
    }

}
