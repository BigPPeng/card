package com.card.test.pool.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkPool {

    private static WorkPool workPool;

    private static ThreadPoolExecutor threadPoolExecutor;
    private static ArrayBlockingQueue<Runnable> workQueue;


    private WorkPool(int corePoolSize,int maxPoolSize,int queueSize) {
        workQueue = new ArrayBlockingQueue<>(queueSize);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                0L, TimeUnit.MILLISECONDS,
                workQueue, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("error，拒绝新加任务");
            }
        });
    }

    public static WorkPool getInstance() {
        if (workPool == null) {
            synchronized (new Object()) {
                if (workPool == null) {
                    workPool = new WorkPool(4,6,5);
                }
            }
        }
        return workPool;
    }


    public void offer(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public void printStatus() {
        System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
                threadPoolExecutor.getQueue().size() + "，已执行的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
    }

    public void shutDown() {
        threadPoolExecutor.shutdown();
    }

}
