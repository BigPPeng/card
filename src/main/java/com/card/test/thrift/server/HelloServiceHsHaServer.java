package com.card.test.thrift.server;

import com.card.test.thrift.Hello;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * Created by cuihp on 2019/12/20.
 */
public class HelloServiceHsHaServer {
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
         * 半同步半异步的服务端模型
         * 使用一个单独的线程处理IO，一个独立的worker线程池处理消息， 可以并行处理所有请求
         */

        try {
            System.out.println("HelloServiceHsHaServer start....");
            TProcessor processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(port);
            THsHaServer.Args args = new THsHaServer.Args(tNonblockingServerSocket);
            args.processor(processor);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TBinaryProtocol.Factory()); // 传输协议 压缩格式

            // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
            TServer server = new THsHaServer(args);
            server.serve();


        } catch (Exception e) {
            System.out.println(" start error ....");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        HelloServiceHsHaServer hsHaServer = new HelloServiceHsHaServer();
        hsHaServer.startServer();
    }


}
