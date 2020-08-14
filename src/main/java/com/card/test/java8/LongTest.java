package com.card.test.java8;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cuihp on 2020/7/3.
 */
public class LongTest {

    public static void main(String[] args) throws IOException {
        Map<Long,String> a = new HashMap<>();
        a.put(1000000L,"1");

        System.out.println(a.containsKey(new Long(1000000L)));
        System.out.println(a.get(new Long(1000000L)));
        System.out.println(a.get(1000000L));


    }

}
