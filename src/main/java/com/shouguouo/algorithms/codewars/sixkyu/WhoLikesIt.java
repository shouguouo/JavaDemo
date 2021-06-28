package com.shouguouo.algorithms.codewars.sixkyu;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 9:58
 */
public class WhoLikesIt {

    public static String whoLikesIt(String... names) {
        int len = names.length;
        String format = "%s and %s like this";
        if (len == 0) {
            return "no one likes this";
        } else if (len == 1) {
            return names[0] + " likes this";
        } else if (len <= 3){
            String name = String.join(", ", names);
            return String.format(format, name.substring(0, name.lastIndexOf(',')), names[len - 1]);
        } else {
            String name = Arrays.stream(names).limit(2).collect(Collectors.joining(", "));
            return String.format(format, name, len -2 + " others");
        }
    }

    @Test
    public void staticTests() {
        assertEquals("no one likes this", whoLikesIt());
        assertEquals("Peter likes this", whoLikesIt("Peter"));
        assertEquals("Jacob and Alex like this", whoLikesIt("Jacob", "Alex"));
        assertEquals("Max, John and Mark like this", whoLikesIt("Max", "John", "Mark"));
        assertEquals("Alex, Jacob and 2 others like this", whoLikesIt("Alex", "Jacob", "Mark", "Max"));
    }
    
}
