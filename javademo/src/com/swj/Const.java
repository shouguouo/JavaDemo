package com.swj;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wink~ on 2018/9/3.
 */
public class Const {
    public static final String LINUXIP = "127.0.0.1";

    public static void main(String[] args) {
        System.out.println(new BigDecimal("1234567891421423456").setScale(2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("1.4").multiply(new BigDecimal("100")).divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("1").divide(new BigDecimal(2), 0, RoundingMode.HALF_DOWN));
        System.out.println((int) 'a');
        System.out.println((int) 'A');

        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }

}