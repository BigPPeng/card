package com.test.face.jvm;

import java.lang.reflect.Method;

/**
 * Created by cuihp on 2020/11/18.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        /**
         * 类的启动加载器：启动加载这个类的加载器：调用load()
         * 类的定义加载器：实际执行加载这个类的加载器： 调用defineClass
         *
         * 这就意味着真正完成类的加载工作的类加载器和启动这个加载过程的类加载器，有可能不是同一个。
         * 真正完成类的加载工作是通过调用 defineClass 来实现的；而启动类的加载过程是通过调用
         * loadClass 来实现的。前者称为一个类的定义加载器（defining loader），后者称为初始加载器（initiating loader）。
         * 在 Java 虚拟机判断两个类是否相同的时候，使用的是类的定义加载器。也就是说，哪个类加载器启动类的加载过程并不重要，
         * 重要的是最终定义这个类的加载器。
         *
         * 两种类加载器的关联之处在于：一个类的定义加载器是它引用的其它类的初始加载器。
         * 如类 com.example.Outer 引用了类 com.example.Inner ，则由类 com.example.Outer 的定义加载器负责启动类 com.example.Inner 的加载过程。
         */

        javaClassSame();

    }

    /**
     * java类是否相同：1、类的全路径名是否相同。2、类的加载器对象是否相同。
     * 注解：同一个类经不同加载器加载，对象不能互相引用。
     *
     */
    private static void javaClassSame() {
        String path = "/Users/cuihp/Desktop/temp/c";
        String className = "com.cn.hongpeng.Example";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(path);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(path);
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setE", Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印类的加载器
     */
    private static void printClassLoader() {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
