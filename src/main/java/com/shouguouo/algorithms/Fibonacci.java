package com.shouguouo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 避免重复计算 存储利用之前的计算结果
 * Created by swj on 2018/1/29.
 */
public class Fibonacci {

    /**
     * 递归求解斐波那契数列
     * @param n
     * @return
     */
    public static long fibonacciRecursion(long n){
        if (n <= 1) return 1;
        return fibonacciRecursion(n - 2) + fibonacciRecursion(n - 1);
    }

    /**
     * 尾递归求解斐波那契数列
     * @param accPrev 第n-1个斐波那契数
     * @param accNext 第n个斐波那契数
     * @param n 第n个斐波那契数
     * @return 包含了一系列斐波那契的完整计算过程 调用invoke方法启动计算
     */
    public static TailRecursion<Long> fibonacciTailRecursion(final long accPrev, final long accNext, final long n){
        if (n <= 1) return TailInvoke.done(accNext);
        return TailInvoke.call(() -> fibonacciTailRecursion(accNext, accPrev + accNext, n - 1));
    }

    /**
     * 迭代求解斐波那契数列
     * @param n
     * @return
     */
    public static long fibonacciIterator(int n){
        long prev = 1;
        long next = 1;
        long accumulate = 0;
        for (int i = 2; i <= n; i++){
            accumulate = prev + next;
            prev = next;
            next = accumulate;
        }
        return accumulate;
    }

    /**
     * 缓存
     */
    private static final Map<Integer, Long> cache = new HashMap<>();

    /**
     * 使用备忘录模式来利用重复结果
     * 利用 synchronized 和 double check 保证线性安全 但是复用性和可读性差
     * @param n
     * @return
     */
    public static long fibonacciMemo(int n){
        if (n == 0 || n == 1)   return 1;
        Long exceptedNum = cache.get(n);
        if (exceptedNum == null){
            synchronized (cache){
                exceptedNum = cache.get(n);
                if (exceptedNum == null){
                    exceptedNum = fibonacciMemo(n - 2) + fibonacciMemo(n - 1);
                    cache.put(n, exceptedNum);
                }
            }
        }
        return exceptedNum;
    }

    /**
     * java8 map结构新增的computeIfAbsent 第一个参数是key值 第二个是function计算策略
     * 如果key为空 就执行后面的策略 不为空则返回结果
     * @param n
     * @return
     */
    public static long fibonacciMemoOpt(int n){
        if (n == 0 || n == 1) return 1;
        return cache.computeIfAbsent(n, key -> fibonacciMemoOpt(n - 2) + fibonacciMemoOpt(n - 1));
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        //System.out.println(fibonacciRecursion(47)); //递归求解 18266.95 ms
        //System.out.println(fibonacciTailRecursion(1, 1, 47).invoke()); //尾递归求解 109.56 ms 由于分装借口使用lambda造成一定的开销
        //System.out.println(fibonacciIterator(47)); // 迭代求解 0.31 ms
        //System.out.println(fibonacciMemo(47)); // before java8 备忘录模式 1.24 ms
        //System.out.println(fibonacciMemoOpt(47)); // in java8 备忘录模式 96.91 ms 与尾递归类似
        System.out.println(Factorial.fibonacciMemo(47)); // 备忘录模式封装 122.64 ms
        System.out.printf("cost %.2f ms %n", (System.nanoTime() - start) / Math.pow(10, 6));
    }
}
