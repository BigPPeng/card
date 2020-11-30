package com.test.client;

import com.alibaba.fastjson.JSON;
import com.elong.hotel.dc.framework.HttpStatusException;
import com.elong.hotel.dc.util.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuihp on 2020/11/24.
 */
public class DsCtripProductClient {
    public static void main(String[] args) throws IOException, HttpStatusException {

        String[] a = new String[]{"10.72.38.32","10.72.45.15","10.72.45.16","10.172.6.39"};
        String[] strings = get(a, 8081, 100000);
        for (int i = 0; i < strings.length; i++) {
            HashMap<String,Integer> hashMap = JSON.parseObject(strings[i], HashMap.class);
            System.out.println("shard_"+i+" total:"+calAllCtrip(hashMap));
        }
    }


    private static int calAllCtrip(HashMap<String,Integer> hashMap) {
        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
            if (stringIntegerEntry.getKey().startsWith("ctrip1count")) {
                return stringIntegerEntry.getValue();
            }
        }
        return 0;
    }


    private static String[] get(String[] hosts,int port,int timeout) throws IOException, HttpStatusException {
        String[] res = new String[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            String url = "http://dashboard2.mis.elong.com/proxy/"+hosts[i]+":"+port+"/get_product_statistics";
            res[i] = get(url,timeout);
        }
        return res;
    }

    private static String get(String url,int timeout) throws IOException, HttpStatusException {
        return HttpUtils.get(url, timeout);
    }



}
