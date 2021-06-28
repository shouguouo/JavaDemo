package com.shouguouo.base.thread;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Range;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

/**
 * @author shouguouo~
 * @date 2020/7/10 - 22:51
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executorService);
        long start = 0;
        long edge = 10000000000L;
        Stopwatch stopwatch = Stopwatch.createStarted();
        makeThread(start, edge, completionService);
        long sum = 0;
        for (long i = 0; i <= (edge - start)/STEP; i++) {
            Future<Long> sb = completionService.take();
            sum += sb.get();
        }
        System.out.println(sum);
        stopwatch.stop();
        System.out.println("parallel consume: " + stopwatch.elapsed(TimeUnit.MICROSECONDS));
        executorService.shutdown();

        stopwatch.reset();
        stopwatch.start();
        long streamSum = LongStream.rangeClosed(start, edge).sum();
        stopwatch.stop();
        System.out.println(streamSum);
        System.out.println("stream consume: " + stopwatch.elapsed(TimeUnit.MICROSECONDS));

        stopwatch.reset();
        stopwatch.start();
        long parallelStreamSum = LongStream.rangeClosed(start, edge).parallel().sum();
        stopwatch.stop();
        System.out.println(parallelStreamSum);
        System.out.println("parallel stream consume: " + stopwatch.elapsed(TimeUnit.MICROSECONDS));

    }

    public static final long STEP = 5000;

    public static void makeThread(long start, long end, CompletionService<Long> completionService) {
        if (start + STEP >= end) {
            completionService.submit(new ComputeThread(Range.closed(start, end)));
            return;
        }
        completionService.submit(new ComputeThread(Range.closed(start, start)));
        do {
            long temp = start + STEP;
            if (temp > end) {
                temp = end;
            }
            completionService.submit(new ComputeThread(Range.closed(start + 1, temp)));
            start = temp;
        } while (start < end);
    }
}

class ComputeThread implements Callable<Long> {

    private Range<Long> range;

    public static AtomicInteger count = new AtomicInteger(0);
    public ComputeThread(Range<Long> range) {
        this.range = range;
    }

    @Override
    public Long call() {
        count.incrementAndGet();
        return LongStream.rangeClosed(range.lowerEndpoint(), range.upperEndpoint()).sum();
    }
}