package com.card.test.thrift.client;


import com.card.test.thrift.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClientThread implements Runnable{

    private String clientName;

    public ThriftClientThread(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void run() {
        System.out.println("客户端启动....");
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 9898, 30000);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            transport.open();
            for (int i = 0; i < 10; i++) {
                String par = clientName + " 崔洪鹏 " + i;
                String result = client.helloWorld(par);
                System.out.println(clientName + " par:"+par+" result:"+result);
                Thread.sleep(2000);
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
