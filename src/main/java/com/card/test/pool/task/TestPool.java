package com.card.test.pool.task;

import java.util.Random;
import java.util.concurrent.*;

public class TestPool {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Task task;
            if (i % 2 == 0) {
                task = new TaskOne();
            } else {
                task = new TaskTwo();
            }
            WorkPool2.getInstance().offer(task);
            WorkPool2.getInstance().printStatus();
            int i1 = random.nextInt(10);
            Thread.sleep(i1 * 20);
        }
        WorkPool2.getInstance().shutDown();
    }



}
