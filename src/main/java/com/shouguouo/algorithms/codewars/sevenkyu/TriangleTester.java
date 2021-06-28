package com.shouguouo.algorithms.codewars.sevenkyu;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 10:07
 */
public class TriangleTester {

    public static boolean isTriangle(int a, int b, int c){
        if (a > b && a > c) {
            return a < b + c;
        } else if (b > a && b > c) {
            return b < a + c;
        } else {
            return c < a + b;
        }
    }

}
