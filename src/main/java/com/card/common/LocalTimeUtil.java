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


}
