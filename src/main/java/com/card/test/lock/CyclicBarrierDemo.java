package com.card.test.lock;

import com.card.test.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by cuihp on 2020/3/6.
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /*
         * CyclicBarrierTest 集齐七颗龙珠，再召唤神龙
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("召唤神龙");
            ThreadUtil.sleep(2000);
            System.out.println(" 神龙over");
        });

        for (int i = 0; i < 10; i++) {
            final int tempInt;
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ " 收集第"+ finalI +"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ " over");
            },String.valueOf(i)).start();
        }




    }

}
