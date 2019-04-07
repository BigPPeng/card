package com.card.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongpeng.cui on 2019/4/7.
 */
public class DateToolUtil {

    public static String format(String formatPatten, long mill) {
        Date date = new Date();
        date.setTime(mill*1000);
        return format(formatPatten,date);
    }


    public static String format(String formatPatten, Date date) {
        return new SimpleDateFormat(formatPatten).format(date);
    }


    public static void main(String[] args){
        long now = System.currentTimeMillis();
        System.out.println(now);
        System.out.println(format("yyyy-MM-dd HH:mm:ss",now));
    }


}
