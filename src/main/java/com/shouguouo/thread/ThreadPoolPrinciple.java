package com.shouguouo.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程池的原理与结构
 * 线程池的好处：1.降低资源消耗 2.提高响应速度 3.提高线程的可管理性
 * Created by swj on 2018/1/21.
 */
public class ThreadPoolPrinciple {

    /**
     * 当把一个runnable交给线程池去处理时
     * 先判断线程池中的核心线程们是否空闲，如果空闲，就把这个新的任务指派给某一个空闲线程去执行。
     * 如果没有空闲，并且当前线程池中的核心线程数还小于 corePoolSize，那就再创建一个核心线程。
     * 如果线程池的线程数已经达到核心线程数，并且这些线程都繁忙，就把这个新来的任务放到等待队列中去。
     * 如果等待队列又满了，那么查看一下当前线程数是否到达maximumPoolSize，如果还未到达，就继续创建线程。
     * 如果已经到达了，就交给RejectedExecutionHandler来决定怎么处理这个任务。
     */
    private static Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i ++){
            executor.execute(new Task());
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    /**
     * workQueue 用来保存等待被执行的任务的阻塞队列 且任务必须实现Runnable接口
     * ArrayBlockingQueue:基于数组结构的有界阻塞队列
     * LinkedBlockingQueue:基于链表的阻塞队列 按FIFO排序任务 吞吐量通常高于ArrayBlockingQueue
     * SynchronousQueue:一个不存储元素的阻塞队列 每个插入操作必须等到另一个线程调用移出操作 否则插入一直处于阻塞状态 吞吐量通常高于LinkedBlockingQueue
     * priorityBlockingQueue:具有优先级的无界阻塞队列
     */


    /**
     * 线程池内部有一个这样的变量
     * private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
     * 利用低29位表示线程池中线程数 通过高3位表示线程池的运行状态 对应的运行状态：
     * private static final int RUNNING    = -1 << COUNT_BITS;即高三位是111----线程创建后的状态 能接收任务 能处理
     * private static final int SHUTDOWN   =  0 << COUNT_BITS;即高三位是000----调用了shutdown方法 不接收任务 能处理 阻塞队列为空 执行的任务也为空->TIDYING
     * private static final int STOP       =  1 << COUNT_BITS;即高三位是001----调用了shutdownNow方法 不接收任务 不处理 当阻塞队列为空 执行的任务也为空->TIDYING
     * private static final int TIDYING    =  2 << COUNT_BITS;即高三位是010----所有任务终止了 会执行terminated方法->TERMINATED
     * private static final int TERMINATED =  3 << COUNT_BITS;即高三位是011----线程池彻底终止
     */
    /**
     * Worker内部类 工作线程的逻辑
     * 即实现了Runnable接口又继承了AbstractQueuedSynchronized(AQS)不可重入锁
     *
     */

    /**
     * runWorker方法是线程池的核心
     * 1.线程启动后 通过unlock方法释放锁 设置AQS的state为0 表示运行中断
     * 2.获取第一个任务firstTask 执行任务的run方法 执行之前会进行加锁操作 任务执行完会释放锁
     * 3.在执行任务的前后 可以根据业务场景自定义beforeExecute和afterExecute方法
     * 4.firstTask任务执行完成之后 通过getTask方法从阻塞队列中获取等待的任务 如果队列中没有任务 getTask会被阻塞挂起 不会占用CPU资源
     */

    /**
     * getTask操作在自旋下完成
     *  Runnable r = timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
     * 1.workQueue.take：如果阻塞队列为空 当前线程会被挂起等待 当队列中有任务加入时 线程被唤醒 take方法返回任务 并执行
     * 2.workQueue.poll：如果在keepAliveTime时间内，阻塞队列还是没有任务 则返回null
     * 所以线程池中可以一直执行由用户提交的任务
     */

    /**
     * RejectedExecutionHandler
     * 当队列和线程池都满了的时候，再有新的任务到达，就必须要有一种办法来处理新来的任务。
     * Java线程池中提供了以下四种策略
     * AbortPolicy: 直接抛异常 默认
     * CallerRunsPolicy：让调用者帮着跑这个任务
     * DiscardOldestPolicy：丢弃队列里最老的那个任务，执行当前任务
     * DiscardPolicy：不处理，直接扔掉
     */


}
