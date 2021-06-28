package com.shouguouo.base.thread;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class ParallelTest extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public ParallelTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0L;
            for(long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        long middle = (start + end) /2;
        ParallelTest parallelTest1 = new ParallelTest(start, middle);
        ParallelTest parallelTest2 = new ParallelTest(middle + 1, end);
        invokeAll(parallelTest1, parallelTest2); // 防止本线程闲置
        return parallelTest1.join() + parallelTest2.join();
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        long result = 0;
        long size = 100000000000L;

        // 计算结果： 932356074711512064，耗时：30579
        for (long i = 0; i <= size; i++) {
            result += i;
        }

        Instant end = Instant.now();
        System.out.println("result: " + result + ", duration: " + Duration.between(start, end).toMillis());

        // 计算结果： 932356074711512064，耗时：27025
        start = Instant.now();
        ForkJoinPool fjp = new ForkJoinPool(4);
        ForkJoinTask<Long> task = new ParallelTest(0, size);
        result = fjp.invoke(task);
        end = Instant.now();
        System.out.println("result: " + result + ", duration: " + Duration.between(start, end).toMillis());

        // 计算结果： 932356074711512064，耗时：43651
        start = Instant.now();
        result = LongStream.rangeClosed(0, size).reduce(0, Long::sum);
        end = Instant.now();
        System.out.println("result: " + result + ", duration: " + Duration.between(start, end).toMillis());

        // 计算结果： 932356074711512064，耗时：17492
        start = Instant.now();
        result = LongStream.rangeClosed(0, size).parallel().reduce(0, Long::sum);
        end = Instant.now();
        System.out.println("result: " + result + ", duration: " + Duration.between(start, end).toMillis());

    }
}
