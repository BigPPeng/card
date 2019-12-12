package com.card.test.thrift.server;

import com.card.test.thrift.Hello;
import org.apache.thrift.TException;


public class HelloImpl implements Hello.Iface {
    @Override
    public String helloWorld(String par) throws TException {
        System.out.println("param:"+par);
        return "Hello " + par;
    }
}
