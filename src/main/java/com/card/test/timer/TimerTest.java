package com.card.test.timer;

import com.card.test.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cuihp on 2020/4/3.
 */
public class TimerTest extends AbstractLoaderTimer {

    private AtomicLong atomicLong =  new AtomicLong(0);

    public TimerTest() {
        super("test");
    }

    public TimerTest(String threadName) {
        super(threadName);
    }

    public TimerTest(long period, TimeUnit timeUnit, String threadName) {
        super(period, timeUnit, threadName);
    }

    @Override
    public void execute() {
        System.out.println("Test and Sleep 2s start" + atomicLong.incrementAndGet());
        ThreadUtil.sleep(2000);
        System.out.println("Test and Sleep 2s end" + atomicLong.get());

    }
}
