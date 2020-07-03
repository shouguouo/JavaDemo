package com.swj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 允许n个任务同时访问某个资源。比如本例中的对象池
 * @author wink~
 * @date 2020/7/3 - 11:02
 */
public class SemaphoreTest {
    final static int SIZE = 5;
    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckOutTask<>(pool));
        }
        System.out.println("All CheckOutTask created");
        List<Fat> fat = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.println(i + " main thread checked out");
            f.operation();
            fat.add(f);
        }
        Future<?> future = executorService.submit(() -> {
            try {
                pool.checkOut();
            } catch (InterruptedException e) {
                System.out.println("Main Thread CheckOut Interrupted");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        future.cancel(true);
        System.out.println("checking in objects in " + fat);
        for (Fat f:
             fat) {
            pool.checkIn(f);
        }
        // ignored
        for (Fat f:
            fat) {
            pool.checkIn(f);
        }
        executorService.shutdown();
    }
}

class CheckOutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckOutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckOutTask id:" + id;
    }
}

class Pool<T> {
    private List<T> items = new ArrayList<>();
    private boolean[] status;

    private Semaphore semaphore;

    public Pool(Class<T> clazz, int size) {
        status = new boolean[size];
        semaphore = new Semaphore(size, true);
        try {
            for (int i = 0; i < size; i++) {
                items.add(clazz.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T checkOut() throws InterruptedException {
        semaphore.acquire();
        return getItem();
    }

    public void checkIn(T t) {
        if (release(t)) {
            semaphore.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < status.length; i++) {
            if (!status[i]) {
                status[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean release(T x) {
        int index = items.indexOf(x);
        if (index >= 0) {
            status[index] = false;
            return true;
        }
        return false;
    }
}

class Fat{
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        for (int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id:" + id;
    }
}