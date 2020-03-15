package com.card.test.thrift.server;


import com.card.test.thrift.Hello;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class HelloServiceTThreadSelectorServer {
    private static final int port = 9898;

    public void startServer() {
        /*
         * TThreadedSelectorServer:
         *
         * TThreadedSelectorServer是thrift 0.8引入的实现，处理IO也使用了线程池，
         * 比THsHaServer有更高的吞吐量和更低的时延，与TThreadPoolServer比性能相近且能应对网络IO较多的情况对于客户端较少的情况，
         * TThreadPoolServer也有优异的性能表现，但是考虑到未来SOA可能的高并发，决定采用TThreadedSelectorServer
         * */

        try {
            System.out.println("HelloServiceTThreadSelectorServer start....");
            TProcessor tProcessor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(port);
            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport);
            args.processor(tProcessor);
            args.protocolFactory(new TCompactProtocol.Factory());// 传输协议 二进制格式
            args.transportFactory(new TFramedTransport.Factory());
            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求
            TServer server = new TThreadedSelectorServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("HelloServiceTThreadSelectorServer start.... error");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        HelloServiceTThreadSelectorServer serviceThreadPoolServer = new HelloServiceTThreadSelectorServer();
        serviceThreadPoolServer.startServer();

    }


}
