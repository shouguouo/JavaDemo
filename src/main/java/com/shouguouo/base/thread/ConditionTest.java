package com.shouguouo.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 新的线程通信方式 替代Object.wait() notify() notifyAll()
 * @author shouguouo~
 * @date 2020/6/9 - 21:30
 */
public class ConditionTest {
    public static void main(String[] args) {
        OrderLoopPrintln olp = new OrderLoopPrintln();
        new Thread(() -> {
            for (int i = 1; i < 21; i++) {
                olp.printlnA(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 21; i++) {
                olp.printlnB(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 21; i++) {
                olp.printlnC(i);
            }
        }, "C").start();
    }
}

class OrderLoopPrintln {
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    private int current = 1;

    public void printlnA(int total) {
        lock.lock();
        try {
            if (current != 1) {
                condition1.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + total);
            }
            current = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printlnB(int total) {
        lock.lock();
        try {
            if (current != 2) {
                condition2.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + total);
            }
            current = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printlnC(int total) {
        lock.lock();
        try {
            if (current != 3) {
                condition3.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + total);
            }
            current = 1;
            condition1.signal();
            System.out.println("-------------------------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}