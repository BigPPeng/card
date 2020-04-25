package com.card.test.thrift.server;

import com.card.test.ThreadUtil;
import com.card.test.thrift.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by cuihp on 2020/4/24.
 */
public class HelloAsyncImpl implements Hello.AsyncIface {
    @Override
    public void helloWorld(String par, AsyncMethodCallback resultHandler) throws TException {
        String respose = "param:" + par;
        System.out.println(respose);
        int random = ThreadUtil.random();
        System.out.println("sleep "+random+"s");
        ThreadUtil.sleep(random * 1000);
        resultHandler.onComplete(respose);
    }
}
