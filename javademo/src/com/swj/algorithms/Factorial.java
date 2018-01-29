package com.swj.algorithms;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 封装备忘录模式
 * Created by swj on 2018/1/29.
 */
public class Factorial {

    /**
     * 备忘录模式函数封装
     * @param function 递归策略算法
     * @param input 输入值
     * @param <T> 输入值类型
     * @param <R> 返回值类型
     * @return 将输入值输入到递归算法 计算出最终结果
     */
    public static <T, R> R callMemo(final BiFunction<Function<T, R>, T, R> function,
                                    final T input){
        Function<T, R> memo = new Function<T, R>() {
            private final Map<T, R> cache = new HashMap<>(64);
            @Override
            public R apply(final T input) {
                return cache.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };
        return memo.apply(input);
    }

    /**
     * 使用统一封装的备忘录模式 对外开放 在内部执行具体的斐波那契策略{@link #fibonacciCallMemo(Function, Integer)}
     * @param n 第n个斐波那契数
     * @return 第n个斐波那契数
     */
    public static long fibonacciMemo(int n){
        return callMemo(Factorial::fibonacciCallMemo, n);
    }

    /**
     * 私有方法 服务用{@link #fibonacciMemo(int)} 内部实现斐波那契算法
     * @param fib 斐波那契算法本身 用于递归调用 在{@link #callMemo(BiFunction, Object)}中通过传入this来实现这个策略
     * @param n 第n个斐波那契数
     * @return 第n个斐波那契数
     */
    private static long fibonacciCallMemo(Function<Integer, Long> fib, Integer n){
        if (n == 0 || n ==1) return 1;
        return fib.apply(n -1) + fib.apply(n - 2);
    }

    /**
     * 转换汉诺塔参数并调用
     * @param n 盘子数量
     * @param from
     * @param mid
     * @param to
     * @return
     */
    public static  boolean movePlateMemo(int n, String from, String mid, String to){
        Object[] params = {n, from, mid, to};
        return callMemo(Factorial::movePlateCallMemo, params);
    }
    /**
     * 汉诺塔问题
     * @param function
     * @param params
     * @return
     */
    private static boolean movePlateCallMemo(Function<Object[], Boolean> function, Object[] params){
        int n = (int) params[0];
        String from = (String) params[1];
        String mid = (String) params[2];
        String to = (String) params[3];
        if (n <= 1){
            System.out.println(from + " -> " + to);
            return true;
        }
        function.apply(new Object[]{n-1, from, to, mid});
        System.out.println(from + " -> "  + to);
        function.apply(new Object[]{n-1, mid, from, to});
        return false;
    }

    //价格的列表 index对应length - 1
    private static final List<Integer> prices = Lists.newArrayList(1, 5, 8, 9, 10,
            17, 17, 20, 24, 30);

    /**
     * 备忘录优化杠切割问题
     * @param rodLength
     * @return
     */
    public static int maxProfit(final int rodLength){
        return callMemo(
                (func, length) ->{
                    int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
                    for (int i = 1; i < length; i++){
                        int priceWhenCut = func.apply(i) + func.apply(length - i);
                        if(profit < priceWhenCut) profit = priceWhenCut;
                    }
                    return profit;
        }, rodLength);
    }
}
