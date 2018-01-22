package com.swj.thread;

/**
 * 线程池的原理与结构
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

    /**
     * Worker内部类 工作线程的逻辑
     */

    /**
     * RejectedExecutionHandler
     * 当队列和线程池都满了的时候，再有新的任务到达，就必须要有一种办法来处理新来的任务。
     * Java线程池中提供了以下四种策略
     * AbortPolicy: 直接抛异常
     * CallerRunsPolicy：让调用者帮着跑这个任务
     * DiscardOldestPolicy：丢弃队列里最老的那个任务，执行当前任务
     * DiscardPolicy：不处理，直接扔掉
     */
}
