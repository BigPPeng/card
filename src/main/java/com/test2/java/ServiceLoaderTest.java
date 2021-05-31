package com.test2.java;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderTest {


    /**
     * SPI全称Service Provider Interface，是Java提供的一套用来被第三方实现或者扩展的API，它可以用来启用框架扩展和替换组件。
     *
     * https://www.jianshu.com/p/46b42f7f593c
     */
    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while (iterator.hasNext()){
            Search next = iterator.next();
            next.search("Hello World");
        }
    }
}
