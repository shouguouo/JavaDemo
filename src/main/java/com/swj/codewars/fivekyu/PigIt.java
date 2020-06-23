package com.swj.codewars.fivekyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 功能说明:
 *
 * @author wink~
 * @date 2020/3/2 - 9:55
 */
public class PigIt {

    public static String pigIt(String str) {
        final String AY = "ay";
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        char firstChar = ' ';
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isCase(c)) {
                if (flag) {
                    firstChar = c;
                    flag = false;
                } else if (i == str.length() - 1 || !isCase(str.charAt(i + 1))){
                    sb.append(c);
                    sb.append(firstChar).append(AY);
                    flag = true;
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }
    public static boolean isCase(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    @Test
    public void FixedTests() {
        assertEquals("igPay atinlay siay oolcay", pigIt("Pig latin is cool"));
        assertEquals("hisTay siay ymay tringsay", pigIt("This is my string"));
    }

}
