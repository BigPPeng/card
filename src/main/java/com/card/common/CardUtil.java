package com.card.common;

import com.google.common.base.Strings;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * Created by hongpeng.cui on 2019/4/1.
 */
public class CardUtil {

    public static boolean isEmail(String s) {
        if (Strings.isNullOrEmpty(s))
            return false;
        if (s.contains("@"))
            return true;
        return false;
    }

    public static boolean isPhoneNumber(String s) {
        if (Strings.isNullOrEmpty(s))
            return false;
        // 长度是11位 && 以'1'开头 && 是数字
        if (s.trim().length() == 11 && s.trim().startsWith("1") && isNumber(s.trim())) {
            return true;
        }
        return false;
    }
    


    public static boolean isNumber(String s) {
        if (Strings.isNullOrEmpty(s))
            return false;
        for (char c : s.toCharArray()) {
            if (c > '9' || c < '0')
                return false;
        }
        return true;
    }


    public static String getCardNumber(){
        Random random = new Random(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < 17) {
            int a = random.nextInt();
            if (a < 0)
                continue;
            int b = a % 10;
            if (i == 0 && b == 0)
                continue;
            builder.append(b);
            i++;
        }
        return builder.toString();
    }



}
