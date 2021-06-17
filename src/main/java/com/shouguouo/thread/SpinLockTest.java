package com.shouguouo.thread;

/**
 * 自旋锁测试类
 * Created by swj on 2018/1/19.
 */
public class SpinLockTest {
    public int total = 0;
    SpinLock lock = new SpinLock();

    public void incTotal() {
        lock.lock();
        total += 1;
        lock.unlock();
    }

    public void testTwoThreads() throws InterruptedException {

        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 5_000; i++) {
                    incTotal();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 5_000; i++) {
                    incTotal();
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(total);
    }

    public static void main(String[] args) {
        try {
            new SpinLockTest().testTwoThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
