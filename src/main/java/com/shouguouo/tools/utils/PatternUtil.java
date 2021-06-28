package com.shouguouo.tools.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shouguouo~
 * @date 2020/3/26 - 22:25
 */
public class PatternUtil {
    public static List<String> getMatchStringList(String str, String pattern) {
        Pattern pt = Pattern.compile(pattern);
        Matcher match = pt.matcher(str);
        List<String> res = new ArrayList<>();
        while (match.find()) {
            String matchStr = match.group().substring(1, match.group().length() -1 );
            if (!res.contains(matchStr)) {
                res.add(matchStr);
            }
        }
        return res;
    }
}
