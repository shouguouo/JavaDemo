package com.shouguouo.collection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class BigDecimalTest {
    public static BigDecimal from = new BigDecimal("10000211313233322323000052103");
    public static int limit = 1000;

    public static void main(String[] args) {
        List<BigDecimal> res = generate();
        System.out.println(res.toString());
        boolean is = res.contains(new BigDecimal("100002113132333223230000521022"));
        System.out.println(is);
    }

    public static List<BigDecimal> generate() {
        List<BigDecimal> res = new ArrayList<>();
        while (limit > 0) {
            from = from.subtract(new BigDecimal("1"));
            res.add(from);
            limit--;
        }
        return res;
    }
}
