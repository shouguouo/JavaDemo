package com.swj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wink~
 * @date 2020/6/29 - 20:36
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Entrance(i));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Entrance.cancel();

        executorService.shutdown();

        if (!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("some tasks not terminated");
        }
        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum: " + Entrance.sumEntrances());
    }
}

class Count{
    private int count = 0;

    private Random random = new Random(47);

    public synchronized int value(){
        return count;
    }

    public synchronized int increment() {
        int temp = count;
        if (random.nextBoolean()) {
            Thread.yield();
        }
        return (count = ++temp);
    }
}

class Entrance implements Runnable{
    private static Count count = new Count();
    private static List<Entrance> entranceList = new ArrayList<>();

    private int number;

    private final int id;

    private static volatile boolean canceled = false;

    public static void cancel(){ canceled = true; }

    public Entrance(int id) {
        this.id = id;
        entranceList.add(this);
    }
    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Stopping " + this);
        }
    }

    public synchronized int getValue() {
        return this.number;
    }

    public static int getTotalCount() {
        return count.value();
    }

    @Override
    public String toString() {
        return "Entrance " + id + ":" + getValue();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance:
             entranceList) {
            sum += entrance.getValue();
        }
        return sum;
    }
}
