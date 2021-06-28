package com.shouguouo.base.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shouguouo~
 * @date 2020/6/6 - 21:16
 */
public class LockTestBefore8 {
    public static void main(String[] args) {
        DecrementThread dt = new DecrementThread();
        new Thread(dt, "Thread 1").start();
        new Thread(dt, "Thread 2").start();
        new Thread(dt, "Thread 3").start();
    }
}

class DecrementThread implements Runnable {

    private AtomicInteger primary = new AtomicInteger(100);

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    if (primary.get() > 0) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ": " + primary.decrementAndGet());
                    }
                } finally {
                    if (primary.get() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
