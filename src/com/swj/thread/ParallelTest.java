package com.swj.thread;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class ParallelTest extends RecursiveTask<Long> {

    private static final int THRESHOLD = 100;
    private Test[] array;
    private int start;
    private int end;

    public ParallelTest(Test[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0L;
            for (int i = start; i < end; i++) {
                sum += array[i].getI();
            }
            return sum;
        }
        int middle = (start + end) /2;
        ParallelTest parallelTest1 = new ParallelTest(array, start, middle);
        ParallelTest parallelTest2 = new ParallelTest(array, middle, end);
        invokeAll(parallelTest1, parallelTest2);
        return parallelTest1.join() + parallelTest2.join();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Test[] arr = new Test[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Test(i);
        }
        long middle = System.currentTimeMillis();
        System.out.println("赋值结束： " + (middle- start));

        /**
         *         赋值结束： 2589
         *         计算结果： 49999995000000，耗时：2896
         *         ForkJoinPool fjp = new ForkJoinPool(4);
         *         ForkJoinTask<Long> task = new ParallelTest(arr, 0, arr.length);
         *         long result = fjp.invoke(task);
         *
        */
        /**
         *
         *         赋值结束： 2526
         *         计算结果： 49999995000000，耗时：24
         *
         *         long result = 0;
         *         for (int i = 0; i < arr.length; i++) {
         *             result += arr[i].getI();
         *         }
        */
        /**
         *          赋值结束： 2483
         *          计算结果： 49999995000000，耗时：2952
         *          long result = Arrays.stream(arr).map(Test::getI).reduce(0L, (a, b) ->  a + b);
         */
        /**
         *          赋值结束： 2614
         *          计算结果： 49999995000000，耗时：2974
         *          long result  = Arrays.stream(arr).parallel().map(Test::getI).reduce(0L, (a, b) ->  a + b);
         */
        /**
         * 赋值结束： 2483
         * 计算结果： 49999995000000，耗时：23
         */
/*        long result = 0;
        for (Test test : arr) {
            result += test.getI();
        }
        System.out.println("计算结果： " + result + "，耗时：" + (System.currentTimeMillis() - middle));*/

    }
}

class Test {
    private long i;

    public Test(long i) {
        this.i = i;
    }

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }
}
