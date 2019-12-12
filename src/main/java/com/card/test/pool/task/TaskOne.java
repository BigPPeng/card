package com.card.test.pool.task;

/**
 * Created by cuihp on 2019/12/4.
 */
public class TaskOne implements Task {
    @Override
    public void runWork() {
        System.out.println("task one run");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
