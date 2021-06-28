package com.shouguouo.base.system;

import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/14 - 21:01
 */
public class ShutdownHookTest {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("shutdown now")));
        System.out.println("start");
        new Thread(() -> {
            System.out.println("waiting...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }).start();
        for (int i = 0; i < 10000000; i++) {
            if (i == 1000000) {
                System.exit(999);
            }
        }
    }
}
