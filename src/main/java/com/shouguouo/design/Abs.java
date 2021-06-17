package com.shouguouo.design;

/**
 * @author shouguouo~
 * @date 2020/4/21 - 14:29
 */
public abstract class Abs {

    void calc(){
        System.out.println("super calc");
    }


    protected void hello(){
        if (isHello()) {
            System.out.println("hello");
        } else {
            System.out.println("no");
        }
    }
    protected boolean isHello() {
        return true;
    }
}
