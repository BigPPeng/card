package com.card.test.collection;

import com.card.test.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cuihp on 2020/3/6.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        Share share = new Share(new ArrayBlockingQueue<String>(10));
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println("生产者启动"+Thread.currentThread().getName());
                share.produce();
            },"AA"+String.valueOf(i)).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("消费者启动"+Thread.currentThread().getName());
                share.consumer();
            },"BB"+String.valueOf(i)).start();

        }
        ThreadUtil.sleep(10000);
        share.stop();
        System.out.println("stop");
    }
}

class Share {
    private volatile boolean FLAG = true;
    private volatile AtomicLong atomicLong = new AtomicLong();
    private BlockingQueue<String> queue = null;

    public Share(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void produce() {
        String data = null;
        boolean offer = false;
        while (FLAG) {
            data = atomicLong.incrementAndGet() + "";
            try {
                offer = queue.offer(data, 2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (offer) {
                System.out.println(Thread.currentThread().getName()+"\t 提交成功"+data);
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 提交失败"+data);
            }
            ThreadUtil.sleep(1000);
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止生产");
    }


    public void consumer() {
        String result = null;
        while (FLAG) {
            try {
                result = queue.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result != null) {
                System.out.println(Thread.currentThread().getName()+"\t 消费成功"+ result);
            }
            if (result == null && FLAG) {
                continue;
            }
            if (result == null && !FLAG) {
                break;
            }
        }
        try {
            result = queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result != null) {
            System.out.println(Thread.currentThread().getName()+"\t 消费成功"+ result);
        }
        FLAG = false;
        System.out.println(Thread.currentThread().getName()+"\t 消费停止");
    }

    public void stop() {
        FLAG = false;
    }

}