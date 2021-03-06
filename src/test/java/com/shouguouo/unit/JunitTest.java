package com.shouguouo.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class JunitTest {


    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r = r * i;
        }
        return r;
    }


    @Test
    public void testFact() {
        assertEquals(1, fact(1));
        assertEquals(2, fact(2));
        assertEquals(6, fact(3));
        assertEquals(3628800, fact(10));
        assertEquals(2432902008176640000L, fact(20));
    }

}
