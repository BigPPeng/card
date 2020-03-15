package com.card.test.pool.self;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cuihp on 2019/12/22.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_SIZE = 10;
    private static final int DEFAULT_WORKER_SIZE = 5;
    private static final int MIN_WORKER_SIZE = 1;

    private volatile AtomicLong DONE;

//    private final LinkedList<Job> jobs = new LinkedList<>();
    private final BlockingQueue<Job> jobs = new LinkedBlockingDeque<Job>();

    private final List<Worker> works = Collections.synchronizedList(new ArrayList<Worker>(MAX_WORKER_SIZE));
    private int workerNum = DEFAULT_WORKER_SIZE;
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorks(DEFAULT_WORKER_SIZE);
    }

    public DefaultThreadPool(int workerNum) {
        if (workerNum > MAX_WORKER_SIZE) {
            workerNum = MAX_WORKER_SIZE;
        } else if (workerNum < MIN_WORKER_SIZE) {
            workerNum = MIN_WORKER_SIZE;
        }
        initializeWorks(workerNum);
    }


    private void initializeWorks(int defaultWorkerSize) {
         for (int i = 0; i< defaultWorkerSize;i++) {
             Worker worker = new Worker();
             works.add(worker);
             Thread thread = new Thread(worker,"work-"+threadNum.incrementAndGet());
             thread.start();
         }
         DONE = new AtomicLong(0);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            jobs.offer(job);
        }
    }

    @Override
    public void shutdown() {
        for (Worker work : works) {
            work.shutDown();
        }
    }

    @Override
    public void addWorks(int num) {
        synchronized (jobs) {
            if (this.workerNum == MAX_WORKER_SIZE) {
                return;
            }
            if (num + this.workerNum > MAX_WORKER_SIZE) {
                num = MAX_WORKER_SIZE - this.workerNum;
            }
            initializeWorks(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorks(int nums) {
        synchronized (jobs) {
            if (nums >= this.workerNum) {
                return;
            }
            int count = 0;
            while (count < nums) {
                Worker worker = works.get(count);
                if (works.remove(worker)) {
                    worker.shutDown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    @Override
    public void printStatus() {
        System.out.println("workSize:"+workerNum+" jobSize:"+jobs.size() + " done:"+DONE.get());
    }

    private class Worker implements Runnable{

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                try {
                    job = jobs.poll(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                synchronized (jobs) {
//                    while (jobs.isEmpty()) {
//                        try {
//                            jobs.wait();
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                            return;
//                        }
//                    }
//                    job = jobs.removeFirst();
//                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        //
                        System.out.println("exception");
                    }
                    DONE.incrementAndGet();
                }
            }
        }


        public void shutDown() {
            running = false;
        }


    }
}
