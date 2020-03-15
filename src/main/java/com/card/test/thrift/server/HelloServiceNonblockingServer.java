package com.card.test.thrift.server;

import com.card.test.thrift.Hello;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * Created by cuihp on 2019/12/19.
 */
public class HelloServiceNonblockingServer {

    private static final int port = 9898;

    public void startServer() {
        /*
         * 服务端和客户端需要指定 TFramedTransport 数据传输的方式。
         * 数据传输协议
         * TBinaryProtocol 二进制格式
         * TCompactProtocol 压缩格式
         * TJSONProtocol JSON格式
         * TSimpleJSONProtocol 提供JSON只写协议，生成的文件很容易通过脚本语言解析
         *
         *
         * TNonblockingServer是单线程非阻塞IO的方式，
         * 通过java.nio.channels.Selector的select()接收连接请求，
         * 但是处理消息仍然是单线程，吞吐量有限不可用于生产
         *
         */

        try {
            System.out.println("HelloServiceNonblockingServer start....");
            TProcessor processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(port);
            TNonblockingServer.Args args = new TNonblockingServer.Args(tNonblockingServerSocket);
            args.processor(processor);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TCompactProtocol.Factory()); // 传输协议 压缩格式

            // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
            TServer server = new TNonblockingServer(args);
            server.serve();


        } catch (Exception e) {
          System.out.println(" start error ....");
          e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        HelloServiceNonblockingServer nonblockingServer = new HelloServiceNonblockingServer();
        nonblockingServer.startServer();
    }


}
