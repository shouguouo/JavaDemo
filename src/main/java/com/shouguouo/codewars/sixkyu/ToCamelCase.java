package com.shouguouo.codewars.sixkyu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 9:58
 */
public class ToCamelCase {

    public static String toCamelCase(String s) {
        Matcher m = Pattern.compile("[_|-](\\w)").matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        return m.appendTail(sb).toString();
    }

}


