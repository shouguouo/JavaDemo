package com.swj.unit;

import com.google.common.collect.Lists;
import com.swj.utils.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wink~
 * @date 2020/4/1 - 10:56
 */
public class ParameterizedTestTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 100})
    void testAbs(int x) {
        assertEquals(x, Math.abs(x));
    }


    @ParameterizedTest
    @MethodSource(value = "testCapitalize")
    void testCapitalize(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    static List<Arguments> testCapitalize() {
        return Lists.newArrayList(Arguments.arguments("abc", "Abc"),
            Arguments.arguments("APPLE", "Apple"),
            Arguments.arguments("gooD", "Good"));
    }

    @ParameterizedTest
    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
    void testCapitalize2(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

}
