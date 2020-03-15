package com.card.test.net;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketServerV1 {

    private static final int port = 16666;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        // server等待连接的到来
        System.out.println("server等待连接的到来");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1014];
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            stringBuilder.append(new String(bytes,0,len,"UTF-8"));
        }
        System.out.println("accept:"+stringBuilder);
        inputStream.close();
        socket.close();
        serverSocket.close();
    }


}
