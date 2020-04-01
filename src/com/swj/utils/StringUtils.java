package com.swj.utils;

/**
 * @author wink~
 * @date 2020/4/1 - 11:10
 */
public class StringUtils {

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
}
