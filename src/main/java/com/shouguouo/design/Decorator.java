package com.shouguouo.design;

/**
 * 装饰者
 * @author swj
 * @date 2018/2/7
 */
public class Decorator {
    /**
     * java.io包中 有个BufferedReader的类
     * 本身就是个reader(继承自Reader) 同时还与一个名为in的成员变量也是Reader类型 只要求是Reader类型的
     * 然后开辟一个数组做缓存
     * 这种本身是一种类型 保持类型接口不变 又能对该类型的其他子类进行加强的能力 就叫装饰者
     * 为对象增加额外能力的设计模式
     */
    /**
     * 源码
     *  public BufferedReader(Reader in, int sz) {
     *      super(in);
     *      if (sz <= 0)
     *          throw new IllegalArgumentException("Buffer size <= 0");
     *      this.in = in;
     *      cb = new char[sz];
     *      nextChar = nChars = 0;
     *  }
     */
}
