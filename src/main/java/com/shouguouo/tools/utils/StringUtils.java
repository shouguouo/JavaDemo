package com.shouguouo.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shouguouo~
 * @date 2020/4/1 - 11:10
 */
public class StringUtils {

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线 */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String blankToLowerCaseMiddleLine(String str) {
        return str.replaceAll("[\\s*?]", Const.MIDDLE_LINE).toLowerCase();
    }
}
