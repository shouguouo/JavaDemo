package com.shouguouo.codewars.sixkyu;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 9:57
 */
public class DuplicateCount {

    public static int duplicateCount(String text) {
        Map map = new HashMap();
        Set set = new HashSet();
        for (char c : text.toCharArray()) {
            if (map.get(c) == null) {
                map.put(c, "OK");
            } else {
                set.add(c);
            }
        }
        return set.size();
    }

    @Test
    public void reallyLongStringContainingDuplicatesReturnsThree() {
        String testThousandA = new String(new char[1000]).replace('\0', 'a');
        String testHundredB = new String(new char[100]).replace('\0', 'b');
        String testTenC = new String(new char[10]).replace('\0', 'c');
        String test1CapitalA = new String(new char[1]).replace('\0', 'A');
        String test1d = new String(new char[1]).replace('\0', 'd');
        String test = test1d + test1CapitalA + testTenC +
            testHundredB + testThousandA;


        assertEquals(3, duplicateCount(test));
    }

    @Test
    public void abcdeReturnsZero() {
        assertEquals(0, duplicateCount("abcde"));
    }

    @Test
    public void abcdeaReturnsOne() {
        assertEquals(1, duplicateCount("abcdea"));
    }

    @Test
    public void indivisibilityReturnsOne() {
        assertEquals(1, duplicateCount("indivisibility"));
    }

}
