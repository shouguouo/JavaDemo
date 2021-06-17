package com.shouguouo.initialization;

/**
 * 属性先声明(分配地址空间)后赋值
 * 赋值按先后顺序
 * @author swj
 * @date 2018/3/14
 */
public class DeclareAndAssign {
    private static int i = 1;

    static {
        i = 100;
    }

    static {
        j = 100;
    }

    private static int j = 1;
    public static void main(String[] args) {
        System.out.println(i);
        System.out.println(j);
    }
}