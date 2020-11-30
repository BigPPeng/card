package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.util.concurrent.Callable;

/**
 * Created by cuihp on 2020/11/10.
 */
public class ThreadBaseTest {
    /**
     * 1、进程，一个程序的一次运行；系统进行资源分配和调度的独立单位。
     * 2、线程，进程独立执行的子任务。
     * 3、extends Thread 或者 implements Runnable 或者 implements Runnable
     * 4、isAlive() 当前线程是否处于活动状态，未结束就是活动状态。
     * 5、Thread.sleep(mills) 线程休眠
     * 6、getName() 获取线程名称
     * 7、getId() 获取线程唯一标识
     * 8、线程停止：
     *      1 使用退出标志，线程正常运行结束，run()方法运行完。
     *      2 使用stop()强制终止。
     *      3 interrupt()方法终止线程。
     *      4
     * 9、suspend(),resume()。线程暂停与重新启动。
     *      1 多线程数据共享数据同步问题
     *      2 锁释放问题，占用锁后线程被永久终止
     * 10、yield() 放弃当前CPU使用，但是时间不确定。
     * 11、isDaemon() 守护线程
     *
     */
    public static void main(String[] args) {
        B target = new B();
        Thread thread = new Thread(target);
        System.out.println(target.getI());
        thread.isDaemon();
        thread.isInterrupted();
        thread.interrupt();

        thread.start();
        ThreadUtil.sleep(10);
        System.out.println(target.getI());
        thread.suspend();
        System.out.println(target.getI());
        ThreadUtil.sleep(100);
        System.out.println(target.getI());
        thread.resume();
        ThreadUtil.sleep(10);
        System.out.println(target.getI());
        thread.interrupt();
        System.out.println(target.getI());
        ThreadUtil.sleep(10);
        System.out.println(target.getI());
    }




}
class A extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " A start");
        ThreadUtil.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " A end");
    }
}


class B implements Runnable {
    private int i;

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " stop");
                    throw new InterruptedException();
                }
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println("stop");
            e.printStackTrace();
        }
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}


class C implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "C");
        return "C";
    }
}