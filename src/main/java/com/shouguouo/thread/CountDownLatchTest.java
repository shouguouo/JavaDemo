package com.shouguouo.thread;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * @author shouguouo~
 * @date 2020/6/5 - 16:26
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10, new CountThreadFactory());
        CountDownLatch cdl = new CountDownLatch(10);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<String>> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(executors.submit(new CountThread(cdl, start)));
        }
      /*  for (Future<String> re : res) {
            System.out.println(re.get());
        }*/
        Stopwatch stopwatch = Stopwatch.createStarted();
        executors.execute(new CountWait(cdl, stopwatch));
        start.countDown();
        System.out.println(stopwatch.elapsed().getNano());
        executors.shutdown();
    }
}

class CountWait implements Runnable {

    private CountDownLatch countDownLatch;

    private Stopwatch stopwatch;

    public CountWait(CountDownLatch countDownLatch, Stopwatch stopwatch) {
        this.countDownLatch = countDownLatch;
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("CountDownLatch Interrupted");
        } finally {
            stopwatch.stop();
        }
        System.out.println("Wait didn't work anymore");
    }
}
class CountThreadFactory implements ThreadFactory {
    private int count = 0;
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("count thread " + ++count);
        return thread;
    }
}

class CountThread implements Callable<String> {

    private CountDownLatch countDownLatch;

    private CountDownLatch start;

    public CountThread(CountDownLatch countDownLatch, CountDownLatch start) {
        this.countDownLatch = countDownLatch;
        this.start = start;
    }

    @Override
    public String call() {
        try {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return Thread.currentThread().getName() + "：this is hangzhou";
        } finally {
            countDownLatch.countDown();
        }
    }
}
