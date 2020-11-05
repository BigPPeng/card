package com.test.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cuihp on 2020/9/10.
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloServiceProxy proxy = new HelloServiceProxy();
        HelloService bind = (HelloService)proxy.bind(new HelloServiceImpl());
        bind.sayHello("哈哈哈");
    }

    private void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Object o = Class.forName(TestService.class.getName()).newInstance();
//        Annotation[] annotations = TestService.class.getAnnotations();
//        for (Annotation annotation : annotations) {
//            System.out.println(annotation.toString());
//        }

        Method[] declaredMethods = TestService.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        Method[] methods = TestService.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        Method sayHello = o.getClass().getMethod("sayHello", String.class);


        sayHello.invoke(o,"崔洪鹏");
    }

}

class TestService {
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
