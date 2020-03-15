package com.card.test.pool.self;

/**
 * Created by cuihp on 2019/12/22.
 */
public interface ThreadPool <Job extends Runnable> {

    void execute(Job job);

    void shutdown();

    void addWorks(int num);

    void removeWorks(int nums);

    int getJobSize();

    void printStatus();
}
