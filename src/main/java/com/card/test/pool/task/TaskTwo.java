package com.card.test.pool.task;

/**
 * Created by cuihp on 2019/12/4.
 */
public class TaskTwo implements Task {
    @Override
    public void runWork() {
        System.out.println("task two run");
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
