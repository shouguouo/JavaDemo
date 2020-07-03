package com.swj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wink~
 * @date 2020/6/5 - 16:26
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10, new CountThreadFactory());
        CountDownLatch cdl = new CountDownLatch(10);
        executors.execute(new CountWait(cdl));
        List<Future<String>> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(executors.submit(new CountThread(cdl)));
        }
        for (Future<String> re : res) {
            System.out.println(re.get());
        }
        executors.shutdown();
    }
}

class CountWait implements Runnable {

    private CountDownLatch countDownLatch;

    public CountWait(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("CountDownLatch Interrupted");
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

    public CountThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() {
        try {
            System.out.println(Thread.currentThread().getName());
            return Thread.currentThread().getName() + "：this is hangzhou";
        } finally {
            countDownLatch.countDown();
        }
    }
}
