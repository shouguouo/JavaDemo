package com.shouguouo.thread;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/3 - 10:00
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayTask> delayTasks = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            delayTasks.put(new DelayTask(random.nextInt(5000)));
        }
        delayTasks.add(new DelayTask.EndSentinel(5000, executorService));
        executorService.execute(new DelayTaskConsumer(delayTasks));
    }
}

class DelayTask implements Runnable, Delayed {

    private static int count = 0;
    private final int id = count++;
    private final int delta;
    private final long trigger;
    protected static List<DelayTask> delayTaskList = new ArrayList<>();

    public DelayTask(int delta) {
        this.delta = delta;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        delayTaskList.add(this);
    }

    @Override
    public int compareTo(@Nonnull Delayed o) {
        DelayTask that = (DelayTask) o;
        return Long.compare(trigger, that.trigger);
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task：" + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public static class EndSentinel extends DelayTask {

        private ExecutorService executorService;
        public EndSentinel(int delta, ExecutorService executorService) {
            super(delta);
            this.executorService = executorService;
        }

        @Override
        public void run() {
            for (DelayTask delayTask : delayTaskList) {
                System.out.println(delayTask.summary() + "  ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdown now");
            executorService.shutdownNow();
        }
    }
}

class DelayTaskConsumer implements Runnable {

    private final DelayQueue<DelayTask> delayTasks;

    public DelayTaskConsumer(DelayQueue<DelayTask> delayTasks) {
        this.delayTasks = delayTasks;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                delayTasks.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
        System.out.println("Consumer finish");
    }
}