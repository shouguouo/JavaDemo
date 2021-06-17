package com.shouguouo.thread;

import java.util.concurrent.TimeUnit;

/**
 * 私有构造函数捕获模式（Private Constructor Capture Idiom）
 *  绕过构造函数无法使用 synchronized 的问题。
 * @author shouguouo~
 * @date 2020/7/8 - 13:17
 */
public class SafePoint {
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // copy constructor
    public SafePoint(SafePoint safePoint) {
        this(safePoint.get());
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "SafePoint{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    public static void main(String[] args) {
        new Thread(() -> {
            final SafePoint safePoint = new SafePoint(1, 1);

            new Thread(() -> {
                safePoint.set(2, 2);
                System.out.println(safePoint);
            }).start();
            new Thread(() -> {
                SafePoint safePoint1 = new SafePoint(safePoint);
                System.out.println("copy: " + safePoint1);
            }).start();
        }).start();
        new Thread(() -> {
            final UnSafePoint unsafePoint = new UnSafePoint(1, 1);

            new Thread(() -> {
                unsafePoint.set(2, 2);
                System.out.println(unsafePoint);
            }).start();
            new Thread(() -> {
                UnSafePoint unSafePoint1 = new UnSafePoint(unsafePoint);
                System.out.println("copy: " + unSafePoint1);
            }).start();
        }).start();
    }
}

class UnSafePoint {
    private int x, y;

    public UnSafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public UnSafePoint(UnSafePoint usp) {
        this(usp.x, usp.y);
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }
    public synchronized void set(int x, int y) {
        this.x = x;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "UnSafePoint{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
