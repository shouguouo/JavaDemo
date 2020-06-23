package com.swj.unit;

/**
 * @author wink~
 * @date 2020/3/31 - 18:05
 */
public class Calculator {
    private long n = 0;

    public long add(long x) {
        n += x;
        return n;
    }

    public long sub(long x) {
        n -= x;
        return n;
    }
}
