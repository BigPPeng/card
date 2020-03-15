package com.card.test.thrift.client;

import com.card.test.thrift.Hello;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 异步客户端
 *
 * Created by cuihp on 2019/12/19.
 */
public class AsyncThriftNonblockingClientThread implements Runnable{
    private static final String ip = "127.0.0.1";
    private static final int port = 9898;
    private static final int timeout = 30000;


    private String clientName;

    public AsyncThriftNonblockingClientThread(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void run() {
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket(ip,port,timeout);
            TProtocolFactory tProtocolFactory = new TCompactProtocol.Factory();
            Hello.AsyncClient asyncClient = new Hello.AsyncClient(tProtocolFactory,clientManager,transport);
            System.out.println("Clint start ...");
            CountDownLatch latch = new CountDownLatch(1);
            AsyncCallBack callBack = new AsyncCallBack(latch);
            System.out.println("call method sayHello start ...");
            asyncClient.helloWorld(clientName + "Cui", callBack);
            System.out.println("call method sayHello .... end");
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Client end.");
    }


    public class AsyncCallBack implements AsyncMethodCallback<String> {

        private CountDownLatch latch;

        public AsyncCallBack(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onComplete(String response) {
            System.out.println("onComplete ...");
            try {
                System.out.println("AsyncCallBack result : " + response);
            } catch (Exception e) {
                System.out.println("error");
            } finally {
                latch.countDown();
            }
        }

        @Override
        public void onError(Exception exception) {
            System.out.println("onError :" + exception.getMessage());
            latch.countDown();
        }
    }

}
