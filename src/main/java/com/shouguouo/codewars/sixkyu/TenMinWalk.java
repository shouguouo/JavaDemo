package com.shouguouo.codewars.sixkyu;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 10:08
 */
public class TenMinWalk {
    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        int x = 0;
        int y = 0;
        for (char step : walk) {
            switch (step) {
                case 'n' : y++;break;
                case 's' : y--;break;
                case 'e' : x++;break;
                case 'w' : x--;break;
            }
        }
        return x == 0 && y == 0;
    }
}
