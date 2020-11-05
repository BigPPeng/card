package com.card.test.net;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by cuihp on 2019/12/23.
 */
public class SocketServerV1 {

    private static final int port = 16666;

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        // server等待连接的到来
        System.out.println("server等待连接的到来");
        InputStream inputStream = null;

        byte[] bytes = new byte[1014];
        int len;

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
                System.out.println("handing clint at "+remoteSocketAddress);
                inputStream = socket.getInputStream();
                while ((len = inputStream.read(bytes)) != -1) {
                    byte[] temp = new byte[len];
                    System.arraycopy(bytes,0,temp,0,len);
                    System.out.println("accept:" + new String(temp));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }


}
