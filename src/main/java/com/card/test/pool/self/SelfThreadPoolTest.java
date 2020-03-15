package com.card.test.pool.self;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2019/12/22.
 */
public class SelfThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool<Runnable> pool = new DefaultThreadPool<>();
        for (int i = 0 ; i < 100 ;i++) {
            pool.execute(new A(i));
            pool.printStatus();
        }
        for (int i = 0 ; i < 100 ;i++) {
            pool.execute(new B(i));
            pool.printStatus();
        }


        int i = 10;
        while (i > 0) {
            ThreadUtil.sleep(1000);
            pool.printStatus();
            i--;
        }

        System.out.println("shutDown");
        pool.printStatus();
        pool.shutdown();
    }


    static class A implements Runnable{

        private int id;

        public A(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("job type A run num:"+id);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class B implements Runnable{

        private int id;

        public B(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("job type B run num:"+id);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
