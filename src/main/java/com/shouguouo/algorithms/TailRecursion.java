package com.shouguouo.algorithms;

import java.util.stream.Stream;

/**
 * Created by swj on 2018/1/26.
 */
public interface TailRecursion<T> {
    /**
     * 用于递归栈帧之间的连接 惰性求值
     * @return
     */
    TailRecursion<T> apply();

    /**
     * 判断当前递归是否结束
     * @return 默认为false 正常的递归过程都没有结束
     */
    default boolean isFinished(){
        return false;
    }

    /**
     * 获取递归结果 只有递归结束之后调用 默认给出异常 通过工具类的重写获取值
     * @return
     */
    default T getResult(){
        throw new Error("递归还没有结束，调用获得结果异常！");
    }

    /**
     * 及早求值 执行者一系列的递归 因为栈帧只有一个 所有使用findFirst获得最终的栈帧 接着调用getResult方法获得最终递归值
     * @return
     */
    default T invoke(){
        return Stream.iterate(this, TailRecursion::apply)
                .filter(TailRecursion::isFinished)
                .findFirst()
                .get()
                .getResult();
    }
}
