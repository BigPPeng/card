package com.test.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cuihp on 2020/9/10.
 */
public class HelloServiceProxy implements InvocationHandler {

    private Object object;

    public Object bind(Object o) {
        this.object = o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是JDK动态代理");
        System.err.println("准备Hello");
        Object invoke = method.invoke(proxy, args);
        System.err.println("结束Hello");
        return invoke;
    }
}
