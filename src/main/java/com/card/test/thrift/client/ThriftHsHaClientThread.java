package com.card.test.thrift.client;

import com.card.test.thrift.Hello;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by cuihp on 2019/12/19.
 */
public class ThriftHsHaClientThread implements Runnable{
    private static final String ip = "127.0.0.1";
    private static final int port = 9898;
    private static final int timeout = 30000;


    private String clientName;

    public ThriftHsHaClientThread(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void run() {
        TTransport tTransport = null;
        try {
            tTransport = new TFramedTransport(new TSocket(ip,port,timeout));
            // 协议与服务端一致
            TProtocol protocol = new TBinaryProtocol(tTransport);
            Hello.Client client = new Hello.Client(protocol);
            tTransport.open();
            for (int i = 0; i < 10; i++) {
                String par = clientName + " Bai " + i;
                String result = client.helloWorld(par);
                System.out.println(clientName + " par:"+par+" result:"+result);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tTransport != null) {
                tTransport.close();
            }
        }
    }
}
