package com.shouguouo.optional;

import java.util.Optional;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class OptionalTest {

  public boolean isShouwj(String name) {
    String opt = Optional.ofNullable(name).orElse("");
    opt = Optional.ofNullable(name).orElseGet(() -> new String());
    opt = Optional.ofNullable(name).orElseThrow(() -> new NullPointerException());
    return opt.equals("Shouwj");
  }

  public static void main(String[] args) {
    System.out.println(new OptionalTest().isShouwj(null));
  }
}
