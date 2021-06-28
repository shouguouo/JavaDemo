package com.shouguouo.base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的运用
 * Created by swj on 2018/1/20.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        AtomicInteger total = new AtomicInteger(0);
        //可以使主线程等着线程池做完工作再退出
        CountDownLatch latch = new CountDownLatch(1000);
        //线程池的基本大小 允许创建的最大线程数 线程大小超过基本大小过多久被销毁 前一个变量的时间单位 任务的排队队列 还有一个线程工厂
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 1000, 10000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        Task task = new Task(total, latch);

        for (int i = 0;i < 1000; i++){
            executor.execute(task);//不断向线程池加任务
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(total.get());
    }
}
class Task implements Runnable {
    AtomicInteger cnt;
    CountDownLatch latch;

    public Task(AtomicInteger cnt, CountDownLatch latch){
        this.cnt = cnt;
        this.latch = latch;
    }
    @Override
    public void run() {
        cnt.incrementAndGet();
        latch.countDown();
    }
}
