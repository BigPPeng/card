package com.card.test.thrift.server;


import com.card.test.thrift.Hello;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

public class HelloServiceThreadPoolServer {
    private static final int port = 9898;

    public void startServer() {
        /*
         * TThreadPoolServer:
         * 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求
         * TThreadPoolServer使用一个专用连接接收connection，
         * 一旦接收到请求就会放入ThreadPoolExecutor中的一个worker里处理，
         * 当请求处理完毕该worker释放并回到线程池中，可以配置线程最大值，当达到线程最大值时请求会被阻塞。
         * TThreadPoolServer性能表现优异，代价是并发高时会创建大量线程
         *
         *
         *
         * TThreadedSelectorServer是thrift 0.8引入的实现，处理IO也使用了线程池，
         * 比THsHaServer有更高的吞吐量和更低的时延，与TThreadPoolServer比性能相近且能应对网络IO较多的情况对于客户端较少的情况，
         * TThreadPoolServer也有优异的性能表现，但是考虑到未来SOA可能的高并发，决定采用TThreadedSelectorServer
         * */

        try {
            System.out.println("HelloServiceThreadPoolServer start....");
            TProcessor tProcessor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            TServerSocket serverTransport = new TServerSocket(port);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(tProcessor);
            args.protocolFactory(new TBinaryProtocol.Factory());// 传输协议 二进制格式
            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求
            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("HelloServiceThreadPoolServer start.... error");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        HelloServiceThreadPoolServer serviceThreadPoolServer = new HelloServiceThreadPoolServer();
        serviceThreadPoolServer.startServer();

    }


}
