package com.card.common;

import com.google.common.base.Strings;
import org.springframework.util.StringUtils;

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


}
