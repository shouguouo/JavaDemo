package com.swj.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wink~
 * @date 2020/6/23 - 9:50
 */
public class InheritableThreadLocalTest {
    private static final ThreadLocal<Context> sb = new InheritableThreadLocal<>();
    // private static final ThreadLocal<Context> sb = new ThreadLocal<>(); // no println

    static class Context {
        String name;
        int value;
    }
    public static void main(String[] args) {
        Context context = new Context();
        context.name = "mainName";
        context.value = 10;
        sb.set(context);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> {
            System.out.println(sb.get().name);
            System.out.println(sb.get().value);
        });
        pool.shutdown();
    }
}
