package com.card.test.timer;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2020/4/3.
 */
public class Test {
    public static void main(String[] args) {
        TimerTest timerTest = new TimerTest("test");
        timerTest.start();


        ThreadUtil.sleep(100000);

    }
}
