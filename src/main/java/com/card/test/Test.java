package com.card.test;

import com.elong.common.util.HttpUtil;
import com.elong.hotel.dc.framework.HttpStatusException;
import com.elong.hotel.dc.util.HttpUtils;
import okhttp3.OkHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.EnumSet;

/**
 * Created by cuihp on 2020/4/22.
 */
public class Test {
    public static void main(String[] args) throws IOException, HttpStatusException {
        String s = HttpUtils.get("http://dashboard2.mis.elong.com/proxy/10.72.38.32:8081/get_product_statistics", 10000);
        System.out.println(s);
    }


    public enum TestEnum {
        A(1,"a"),
        B(2,"b"),
        C(3,"c"),
        D(4,"d")
        ;

        int i;
        String a;

        TestEnum(int i, String a) {
            this.i = i;
            this.a = a;
        }
    }



}
