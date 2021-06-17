package com.shouguouo.design;

/**
 * @author shouguouo~
 * @date 2020/4/21 - 14:31
 */
public class AbsImp2 extends Abs{
    @Override
    void calc() {
        super.calc();
        hello();
    }

    @Override
    protected boolean isHello() {
        return false;
    }
}
