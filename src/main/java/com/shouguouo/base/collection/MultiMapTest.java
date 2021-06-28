package com.shouguouo.base.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author shouguouo~
 * @date 2020/6/6 - 9:19
 */
public class MultiMapTest {
    public static void main(String[] args) {
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("swj", "帅哥");
        map.put("swj", "大帅哥");
        map.put("swj", "帅哥");
        map.put("xhy", "美女");
        for (String name:
             map.keySet()) {
            System.out.println(name + ":" + map.get(name));
        }
    }
}
