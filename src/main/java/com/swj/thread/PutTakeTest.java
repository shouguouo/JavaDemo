package com.swj.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wink~
 * @date 2020/7/21 - 21:48
 */
public class PutTakeTest {
    private static final ExecutorService executors = Executors.newCachedThreadPool();

    private final AtomicInteger putSum = new AtomicInteger(0);

    private final AtomicInteger takeSum = new AtomicInteger(0);

    private final CyclicBarrier barrier;

    private final BarrierTimer timer;

    private final BoundedBuffer<Integer> bb;

    private final int nTrials, nParis;

    public PutTakeTest(int capacity, int nTrials, int nParis) {
        this.bb = new BoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nParis = nParis;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(nParis * 2 + 1, this.timer);
    }

    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nParis; i++) {
                executors.execute(new Producer());
                executors.execute(new Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nParis * (long) nTrials);
            System.out.println("Through put: " + nsPerItem + " ns/item");
            assert putSum.get() == takeSum.get();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class BarrierTimer implements Runnable{
        private boolean started;

        private long start, end;

        @Override
        public synchronized void run() {
            long t = System.nanoTime();
            if (!started) {
                started = true;
                start = t;
            } else {
                end = t;
            }
        }

        public synchronized void clear() {
            started = false;
        }

        public synchronized long getTime() {
            return end - start;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int tpt = 100000;
        for (int cap = 1; cap <=1000 ; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128 ; pairs *= 2) {
                PutTakeTest t = new PutTakeTest(cap, tpt, pairs);
                System.out.println("Pairs: " + pairs + "\t");
                t.test();
                System.out.println("\t");
                TimeUnit.SECONDS.sleep(1);
                t.test();
                System.out.println();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        executors.shutdown();
    }
}


