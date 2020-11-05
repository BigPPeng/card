package com.card.test.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketServerV4 {

    /**
     * 使用一个线程池处理逻辑，不阻塞流程。线程池大小可以调节。
     * 但是接受socket连接的仍是单线程
     */

    private static final int port = 16668;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        // server等待连接的到来
        System.out.println("server等待连接的到来");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            while (true) {
                final Socket socket = serverSocket.accept();
                Runnable runnable = () -> {
                    try {
                        System.out.println("Come in");
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();
                        Scanner scanner = new Scanner(inputStream);
                        PrintWriter printWriter = new PrintWriter(outputStream, true);
                        printWriter.println("Hello! Enter Bye to Exit!");
                        boolean end = false;
                        while (!end && scanner.hasNext()) {
                            String s = scanner.nextLine();
                            printWriter.println("Echo:"+s);
                            if (s.trim().equals("Bye")){
                                end = true;
                            }
                        }

                        outputStream.close();
                        inputStream.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
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
