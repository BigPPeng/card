package com.test.reflect;

/**
 * Created by cuihp on 2020/9/10.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
