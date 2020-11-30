package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by cuihp on 2020/11/16.
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TT());
        new Thread(()->{
            ThreadUtil.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" come");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ok");
        },"T1").start();
        new Thread(()->{
            ThreadUtil.sleep(2000);
            System.out.println(Thread.currentThread().getName()+" come");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ok");
        },"T2").start();

        System.out.println("main come");
        ThreadUtil.sleep(1200);
        cyclicBarrier.await();


        System.out.println("main ok");

    }


    static class TT implements Runnable {

        @Override
        public void run() {
            System.out.println(" all come , let us go");
        }
    }


}
