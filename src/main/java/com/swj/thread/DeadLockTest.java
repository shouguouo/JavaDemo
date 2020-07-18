package com.swj.thread;

import java.util.concurrent.TimeUnit;

/**
 * using jstack + pid to show how deadlock happened
 * Found one Java-level deadlock:
 * =============================
 * "thread 2":
 *   waiting to lock monitor 0x000000001c340c48 (object 0x000000076b878618, a java.lang.Object),
 *   which is held by "thread 1"
 * "thread 1":
 *   waiting to lock monitor 0x000000001c343588 (object 0x000000076b878628, a java.lang.Object),
 *   which is held by "thread 2"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "thread 2":
 *         at com.swj.thread.DeadThread2.run(DeadLockTest.java:46)
 *         - waiting to lock <0x000000076b878618> (a java.lang.Object)
 *         - locked <0x000000076b878628> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 * "thread 1":
 *         at com.swj.thread.DeadThread1.run(DeadLockTest.java:30)
 *         - waiting to lock <0x000000076b878628> (a java.lang.Object)
 *         - locked <0x000000076b878618> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 * @author wink~
 * @date 2020/7/18 - 13:50
 */
public class DeadLockTest {
    public static void main(String[] args) {
        new Thread(new DeadThread1(), "thread 1").start();
        new Thread(new DeadThread2(), "thread 2").start();
    }
}


class DeadThread1 implements Runnable{

    static final Object object1 = new Object();
    static final Object object2 = new Object();

    @Override
    public void run() {
        synchronized (object1) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            synchronized (object2) {
                System.out.println( Thread.currentThread().getName() + ": I got two locks");
            }
        }
    }
}

class DeadThread2 implements Runnable{

    @Override
    public void run() {
        synchronized (DeadThread1.object2) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            synchronized (DeadThread1.object1) {
                System.out.println( Thread.currentThread().getName() + ": I got two locks");
            }
        }
    }
}
