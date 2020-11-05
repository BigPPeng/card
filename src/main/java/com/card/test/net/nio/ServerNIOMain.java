package com.card.test.net.nio;

/**
 * Created by cuihp on 2020/10/10.
 */
public class ServerNIOMain {
    public static void main(String[] args) {
        int port = 9889;
        ServerSocketChannels server = new ServerSocketChannels(port);
        new Thread(server,"timeserver-001").start();
    }
}
