package com.card.test.pool.task;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class WorkPool2 {

    private static WorkPool2 workPool;

    private ExecutorService executorService;
    private ArrayBlockingQueue<Task> workQueue;
    private List<Worker> works;
    private AtomicLong offerSize;
    private AtomicLong endSize;

    private WorkPool2(int corePoolSize, int queueSize) {
        workQueue = new ArrayBlockingQueue<>(queueSize);
        executorService = Executors.newFixedThreadPool(corePoolSize);
        offerSize = new AtomicLong(0);
        endSize = new AtomicLong(0);
        works = Lists.newArrayListWithExpectedSize(4);
        for (int i = 0; i < corePoolSize; i++ ) {
            Worker name = new Worker("Name", workQueue);
            works.add(name);
            executorService.execute(name);
        }
    }

    public static WorkPool2 getInstance() {
        if (workPool == null) {
            synchronized (new Object()) {
                if (workPool == null) {
                    workPool = new WorkPool2(4,6);
                }
            }
        }
        return workPool;
    }


    public boolean offer(Task task) {
        offerSize.getAndIncrement();
        return workQueue.offer(task);
    }

    public void printStatus() {
        System.out.println("队列中等待执行的任务数目：" + workQueue.size() +" 提交数："+offerSize.get() + " 完成数："+endSize.get());
    }

    public void shutDown() {
        boolean run = true;
        while (run) {
            for (Worker work : works) {
                if (work.getRun().get()) {
                    run = true;
                    break;
                }
            }
            run = false;
        }
        executorService.shutdownNow();
    }


    private synchronized void addEndSize() {
        endSize.incrementAndGet();
    }


    class Worker implements Runnable {

        private String name;
        private ArrayBlockingQueue<Task> workQueue;
        private AtomicBoolean run;
        private WorkPool2 workPool2;

        public Worker(String name, ArrayBlockingQueue<Task> workQueue) {
            this.name = name;
            this.workQueue = workQueue;
            this.run = new AtomicBoolean(false);
        }

        @Override
        public void run() {
            System.out.println("Thread name :" + name);
            while (true) {
                try {
                    Task take = workQueue.take();
                    run.set(true);
                    take.runWork();
                    run.set(false);
                } catch (InterruptedException e) {
                    break;
                }
            }

        }


        public AtomicBoolean getRun() {
            return run;
        }
    }


}
