package com.card.test.thrift.server;

import com.card.test.thrift.Hello;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class HelloServiceServer {

    public static void main(String[] args) {

        try {
            System.out.println("服务端开启。。。");
            // 1.创建TProcessor
            TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            // 2.创建TserverTransport
            TServerSocket serverTransport = new TServerSocket(9898);
            // 3.创建TProtocol
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(factory);

            // 4.创建Tserver,传入需要的参数,server将以上内容集成在一起
            TServer server = new TSimpleServer(tArgs);
            // 5.启动server
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }

}
