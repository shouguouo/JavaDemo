package com.shouguouo.codewars.fivekyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 9:56
 */
public class PrimeDecomp {

    public static String factors(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 2;i <= n; i++) {
            int count = 0;
            while (n % i == 0) {
                count++;
                n /= i;
            }
            if (count > 0) {
                res.append("(").append(i).append(count > 1 ? "**" + count : "").append(")");
            }
        }
        if (res.length() == 0) {
            return "(" + n + ")";
        }
        return res.toString();
    }

    @Test
    public void testPrimeDecompOne() {
        int lst = 782611830;
        assertEquals(
            "(2**2)(3**3)(5)(7)(11**2)(17)",
            factors(lst));
    }
}
