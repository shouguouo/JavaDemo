package com.shouguouo.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author shouguouo~
 * @date 2020/7/8 - 14:37
 */
public class IteratorTest {

    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            add(random.nextInt(10));
        }
        synchronized (this) { // without synchronized caught concurrent modification exception
            System.out.println(set);
        }
    }
    public static void main(String[] args) {
        IteratorTest iteratorTest = new IteratorTest();
        for (int i = 0; i < 100; i++) {
            final int b = i;
            new Thread(() -> iteratorTest.add(b)).start();
        }
        new Thread(iteratorTest::addTenThings).start();
        for (int i = 100; i < 200; i++) {
            final int b = i;
            new Thread(() -> iteratorTest.add(b)).start();
        }
    }
}
