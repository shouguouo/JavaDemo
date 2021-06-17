package com.shouguouo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/1 - 10:59
 */
public class WaitNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Wait(), "1").start();
        new Thread(new Wait(), "2").start();
        TimeUnit.SECONDS.sleep(1);
        Wait.notifyObject();
    }
}

class Wait implements Runnable {

    private static final Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "enter waiting lock");
            try {
                object.wait(); // if not hold a lock will caught java.lang.IllegalMonitorStateException;
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }

    public static void notifyObject() {
        synchronized (object) {
            System.out.println("notify all");
            object.notifyAll();
        }
    }
}

