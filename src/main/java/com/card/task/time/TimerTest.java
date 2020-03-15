package com.card.task.time;

import java.util.*;

public class TimerTest {

    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner(",");
//        for (String arg : args) {
//            stringJoiner.add(arg);
//        }
        stringJoiner.add("a");
        stringJoiner.add("b");
        stringJoiner.add("c");
        stringJoiner.add("d");
        System.out.println(stringJoiner.toString());
    }

    private static void timerTe() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                System.out.println("timer1 hour:"+calendar.get(Calendar.HOUR_OF_DAY)+" minute:"+calendar.get(Calendar.MINUTE)+" second"+calendar.get(Calendar.SECOND));
            }
        },10000,1000);

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                System.out.println("timer2 hour:"+calendar.get(Calendar.HOUR_OF_DAY)+" minute:"+calendar.get(Calendar.MINUTE)+" second"+calendar.get(Calendar.SECOND));
            }
        },5000,2000);

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {

        }
        System.out.println("cancel");
        timer.cancel();
        timer1.cancel();
    }

}
