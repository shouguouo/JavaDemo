package com.shouguouo.codewars.sixkyu;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class Order {
    public static String order(String words) {
        return Arrays.stream(words.split(" ")).sorted(Comparator.comparingInt(Order::findInt)).collect(Collectors.joining(" "));
    }

    public static int findInt(String str) {
        for (char c : str.toCharArray()) {
            if (c >= '1' && c <= '9') {
                return Integer.parseInt(Character.toString(c));
            }
        }
        return 0;
    }

    @Test
    public void test1() {
        assertThat(Order.order("is2 Thi1s T4est 3a"), equalTo("Thi1s is2 3a T4est"));
    }

    @Test
    public void test2() {
        assertThat(Order.order("4of Fo1r pe6ople g3ood th5e the2"), equalTo("Fo1r the2 g3ood 4of th5e pe6ople"));
    }
}
