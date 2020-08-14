package com.card.test.ano;

import java.lang.reflect.Field;

/**
 * Created by cuihp on 2020/7/5.
 */
public class MainTest {
    public static void valid(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            MyAn test = field.getAnnotation(MyAn.class);//获取属性上的@Test注解
            if(test != null){
                field.setAccessible(true);//设置属性可访问

                if("class java.lang.String".equals(field.getGenericType().toString())){//字符串类型的才判断长度
                    String value = (String)field.get(obj);
                    if(value != null && ((value.length() > test.max()) || value.length() < test.min())){
                        System.out.println(value + "_" +test.value());
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IllegalAccessException {
        AnnoationTest annoationTest = new AnnoationTest();
        annoationTest.setName("1111");
        annoationTest.setPass("1111");
        valid(annoationTest);

    }


}
