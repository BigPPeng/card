package com.card.test.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by cuihp on 2020/9/14.
 */
public class NioSocketServer {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 16689;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }


    public static void selector() {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("======");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable() && key.isValid()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectAble == true");
                    }
                    iterator.remove();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer attachment = (ByteBuffer) key.attachment();
        attachment.flip();
        SocketChannel channel = (SocketChannel) key.channel();
        while (attachment.hasRemaining()) {
            channel.write(attachment);
        }
        attachment.compact();
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer attachment = (ByteBuffer) key.attachment();
        int read = channel.read(attachment);
        while (read > 0) {
            attachment.flip();
            while(attachment.hasRemaining()){
                System.out.print((char)attachment.get());
            }
            System.out.println();
            attachment.clear();
            read = channel.read(attachment);
        }
        if (read == -1) {
            channel.close();
        }
    }






    private static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel accept = channel.accept();
        accept.configureBlocking(false);
        accept.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));
    }


}
