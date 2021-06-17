package com.shouguouo.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 读写锁
 * 写写/读写 互斥
 * 读读 不需要互斥
 * 写锁 独占式
 * @author shouguouo~
 * @date 2020/6/9 - 21:43
 */
public class ReadWriteLockTest {
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private int number;

    public void get() {
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "：" + number);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void set(int number) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "：" + number);
            this.number = number;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                readWriteLockTest.set(finalI);
            }, "Write" + finalI).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(readWriteLockTest::get, "Read" + i).start();
        }
    }
}
