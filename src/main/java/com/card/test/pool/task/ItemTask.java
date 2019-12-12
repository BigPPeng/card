package com.card.test.pool.task;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;


public class ItemTask implements Runnable {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    private String name;
    private List<Integer> list = Lists.newArrayList();

    public ItemTask(String name) {
        this.name = name;
    }

    private void init() {
        Random random = new Random();
        int i = random.nextInt(1000);
        while (i > 0) {
            list.add(random.nextInt());
            i--;
        }
    }


    private void remove() {
        System.out.println("name:"+name+" length:"+list.size());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println(("name:"+name+" error"));
        }
        list.clear();
    }



    @Override
    public void run() {
        for (int i = 0 ; i < 3; i++) {
            init();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(("name:"+Thread.currentThread().getName()+" error"));
            }
            remove();
        }
    }
}
