package com.card.test.timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cuihp on 2020/4/3.
 */
public abstract class AbstractLoaderTimer implements ITimerExecutor{

    private static final int POOL_SIZE = 3;

    private Thread worker;
    private ScheduledExecutorService executorService;
    private long initialDelay;// 初始化延迟
    private long period;// 传入周期
    private TimeUnit timeUnit;// 时间单位
    private String threadName;// 线程名字
    private AtomicLong atomicLong = new AtomicLong(0);

    private void init() {
        executorService = Executors.newScheduledThreadPool(POOL_SIZE);
    }

    public AbstractLoaderTimer(String threadName) {
        this.period = 1000;
        this.timeUnit = TimeUnit.MILLISECONDS;
        this.threadName = threadName;
    }

    public AbstractLoaderTimer(long period, TimeUnit timeUnit, String threadName) {
        this.period = period;
        this.timeUnit = timeUnit;
        this.threadName = threadName;
    }

    public void start() {
        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                execute();
                System.out.println("count:" + atomicLong.incrementAndGet());
            }
        });
        worker.setName(this.threadName + this.worker.getName());
        init();
        this.executorService.scheduleAtFixedRate(worker, 0, period, timeUnit);
    }

}
