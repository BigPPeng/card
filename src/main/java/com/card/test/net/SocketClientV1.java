package com.card.test.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketClientV1 {




    public static void main(String[] args) throws Exception{
//        for (int i = 0; i < 50; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                long start = System.currentTimeMillis();
//                try {
//                    SocketConnectTest(finalI,16667);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("耗时："+ (System.currentTimeMillis() - start) + " ms");
//
//            },"线程"+i).start();
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 500; i++) {
            int finalI = i;
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                try {
                    SocketConnectTest(finalI,16668);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("耗时："+ (System.currentTimeMillis() - start) + " ms");
            });
        }

        executorService.shutdown();
    }


    private static void SocketConnectTest(int i,int port) throws IOException {
        String host = "127.0.0.1";
        Socket socket = new Socket(host,port);
        OutputStream outputStream = socket.getOutputStream();
        String message = "hello 崔洪鹏， first test" + i;
        outputStream.write(message.getBytes("UTF-8"));

        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len,"UTF-8"));
        }
        System.out.println("Return:"+sb.toString());

        outputStream.close();
        socket.close();
    }


}
