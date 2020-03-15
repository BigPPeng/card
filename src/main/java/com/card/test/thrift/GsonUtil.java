package com.card.test.thrift;


import com.google.gson.Gson;

/**
 * Gson序列化反序列化工具
 * @author Kang.Liu
 */
public class GsonUtil {

    public static String toJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);

    }

    public static <T> T parseObj(String json, Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
