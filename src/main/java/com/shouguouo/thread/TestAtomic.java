package com.shouguouo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用atomic变量保证线程安全
 * 底层的CAS操作
 * Created by swj on 2018/1/20.
 */
public class TestAtomic {
    public AtomicInteger total;

    public static void main(String[] args) {
        TestAtomic test = new TestAtomic();
        test.total = new AtomicInteger(0);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0;i < 5000;i++) {
                    test.total.incrementAndGet();
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0;i < 5000;i++){
                    test.total.incrementAndGet();
                }
            }
        };
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.total);
    }
}
