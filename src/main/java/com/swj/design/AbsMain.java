package com.swj.design;

/**
 * @author wink~
 * @date 2020/4/21 - 14:32
 */
public class AbsMain {
    public static void main(String[] args) {
        Abs abs = new AbsImp1();
        Abs abs1 = new AbsImp2();
        abs.calc();
        abs1.calc();
    }
}
