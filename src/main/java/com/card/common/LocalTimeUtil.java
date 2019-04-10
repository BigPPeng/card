package com.card.common;

import java.util.Calendar;

/**
 * 时间工具
 * Created by hongpeng.cui on 2019/3/27.
 */
public class LocalTimeUtil {
    public static long getCurrentTimeSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 下月十号还款
     */
    public static Long getRePaymentTime(long currentTimeSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeSecond * 1000);
        int month = calendar.get(Calendar.MONTH);

        calendar.set(Calendar.MONTH,month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        return calendar.getTimeInMillis() / 1000;
    }

    public static Long getTargetMonthBeginTime(int year,int month) {
        if (year <= 2000 || year >= 3000)
            year = 2019;
        if (month < 1 || month > 12)
            month = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        return calendar.getTimeInMillis() / 1000;
    }

    public static Long getTargetMonthEndTime(int year,int month) {
        if (year <= 2000 || year >= 3000)
            year = 2019;
        if (month < 1 || month > 12)
            month = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month - 1);
        int day = 30;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        } else if (month == 2) {
            if (isRunNian(year)) {
                day = 29;
            } else {
                day = 28;
            }
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        return calendar.getTimeInMillis() / 1000;
    }

    private static boolean isRunNian(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400) == 0;
    }



}
