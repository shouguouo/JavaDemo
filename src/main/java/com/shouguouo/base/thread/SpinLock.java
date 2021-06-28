package com.shouguouo.base.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 * 例子：去公司的卫生巾蹲坑 一直没位置 这时候由于没有任何的通知机制 我们只能隔一阵子去看看有没有空位 有空位就抢 进去把门锁上，没有就继续等
 * 实现非常的简单 如果关键区（造成并发的区域）的执行时间很短 是一种比较高效的做法 避免线程的频繁切换和调度
 * 如果关键区很长 这种做法就会大量地浪费CPU资源
 * Created by swj on 2018/1/19.
 */
public class SpinLock implements Lock{
    AtomicInteger state = new AtomicInteger(0);

    @Override
    public void lock() {
        for (;;){
            if (state.get() == 1)
                continue;
            else if (state.compareAndSet(0, 1))
                break;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        state.set(0);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
