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
