package com.card.test.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketClientV1 {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            SocketConnectTest(i);
            Thread.sleep(100);
        }
    }

    private static void SocketConnectTest(int i) throws IOException {
        String host = "127.0.0.1";
        int port = 16667;
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
        System.out.println("get return :"+sb.toString());

        outputStream.close();
        socket.close();
    }


}
