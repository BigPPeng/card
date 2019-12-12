package com.card.test.lock;

/**
 * Created by cuihp on 2019/11/21.
 */
public class TestLockCondition {

    public static void main(String[] args) {

        MyService myService = new MyService();

        Thread threadA = new Thread(new MyThreadA(myService));
        threadA.setName("ThreadA");
//        Thread threadAA = new Thread(new MyThreadAA(myService));
//        threadAA.setName("ThreadAA");
        Thread threadB = new Thread(new MyThreadB(myService));
        threadB.setName("ThreadB");
        threadA.start();
        threadB.start();


        myService.signalA();


    }



}


class MyThreadA implements Runnable {

    private MyService myService;

    public MyThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.serviceA();
    }
}
class MyThreadAA implements Runnable {

    private MyService myService;

    public MyThreadAA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.serviceAA();
    }
}
class MyThreadB implements Runnable {

    private MyService myService;

    public MyThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.serviceB();
    }
}