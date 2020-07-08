package com.swj.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class ConcurrentHashMapTest {
  private static final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

  public static void main(String[] args) {
    String sb = "hao";
    concurrentHashMap.put(sb, "好吧");
    concurrentHashMap.put(sb, "不好");

    System.out.println(concurrentHashMap.size());
    System.out.println(concurrentHashMap.get(sb));
    System.out.println("-----------------------------");
    String res = concurrentHashMap.putIfAbsent(sb, "hi");
    System.out.println(concurrentHashMap.get(sb));
    System.out.println(res);
    System.out.println("-----------------------------");
    res = concurrentHashMap.putIfAbsent("hi", "hi");
    System.out.println(concurrentHashMap.get(sb));
    System.out.println(res);
  }

}
