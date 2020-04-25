package com.card.test.timer;

import java.util.concurrent.Callable;

/**
 * Created by cuihp on 2020/4/3.
 */
public interface ITimerExecutor {
    void execute();
}

class thread1 implements Runnable{

    @Override
    public void run() {

    }
}

class thread2 implements Callable<ITimerExecutor>{

    @Override
    public ITimerExecutor call() throws Exception {
        return null;
    }
}

class thread3 extends Thread{

}