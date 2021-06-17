package com.shouguouo.codewars.sixkyu;

import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 9:54
 */
public class StockList {

    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        LinkedHashMap<Character, Integer> containMap = new LinkedHashMap<>();
        for (String art : lstOfArt) {
            char c = art.charAt(0);
            int single = Integer.parseInt(art.split(" ")[1]);
            map.merge(c, single, Integer::sum);
        }
        for (String lst : lstOf1stLetter) {
            Integer total = map.get(lst.charAt(0));
            containMap.put(lst.charAt(0), total == null ? 0 : total);
        }
        if (containMap.values().stream().filter(x -> x == 0).count() == containMap.size()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        containMap.forEach((key, value) -> {
            sb.append("(").append(key).append(" : ").append(value).append(") - ");
        });
        return sb.length() > 0 ? sb.substring(0, sb.length() - 3) : "";
    }


    @Test
    public void test1() {
        String art[] = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String cd[] = new String[] {"A", "B"};
        assertEquals("(A : 200) - (B : 1140)",
            stockSummary(art, cd));
    }
}
