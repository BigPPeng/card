package com.card.test.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketServerV3 {

    /**
     * 使用一个线程池处理逻辑，不阻塞流程。线程池大小可以调节。
     * 但是接受socket连接的仍是单线程
     */

    private static final int port = 16667;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        // server等待连接的到来
        System.out.println("server等待连接的到来");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            while (true) {
                final Socket socket = serverSocket.accept();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            byte[] bytes = new byte[1014];
                            int len;
                            StringBuilder stringBuilder = new StringBuilder();
                            while ((len = inputStream.read(bytes)) != -1) {
                                stringBuilder.append(new String(bytes,0,len,"UTF-8"));
                            }
                            System.out.println("accept:"+stringBuilder);

                            Thread.sleep(1000);
                            OutputStream outputStream = socket.getOutputStream();
                            String s = "return : get" + stringBuilder.toString();
                            outputStream.write(s.getBytes("UTF-8"));

                            outputStream.close();
                            inputStream.close();
                            socket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                executorService.execute(runnable);
            }
        } catch (Exception e) {

        } finally {
            serverSocket.close();
        }
    }


}
