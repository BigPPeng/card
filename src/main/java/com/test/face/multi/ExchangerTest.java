package com.test.face.multi;

import com.card.test.ThreadUtil;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihp on 2020/11/17.
 */
public class ExchangerTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Exchanger<String> stringExchanger = new Exchanger<>();


        executorService.execute(()->{
            try {
                System.out.println("AAA");
                for (int i = 0; i < 10; i++) {
                    String aGet = stringExchanger.exchange("AAAAAAAA"+i);
                    System.out.println("AAA :" + aGet);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            try {
                System.out.println("BBB");
                for (int i = 0; i < 10; i++) {
                    ThreadUtil.sleep(1000);
                    String bGet = stringExchanger.exchange("BBBBBBBB"+i);
                    System.out.println("BBB :" + bGet);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ThreadUtil.sleep(10000);
        executorService.shutdown();
        System.out.println("END");
    }


}
