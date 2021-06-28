package com.shouguouo.base.thread;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 用atomic实现可以等待的锁
 * 适用于关键区的执行时间很长的情况
 * Created by swj on 2018/1/21.
 */

// 1.需要一个容器 如果线程抢不到锁 就把线程挂起来 并记录到这个容器里
// 2.当一个线程放弃了锁 就得从容器里找出一个挂起的线程 把它恢复。
public class WaitLock {
}

class WrongBlockLock implements Lock {
    private AtomicInteger state = new AtomicInteger(0);

    //JDK的无锁队列 也是使用CAS操作实现 不会引入JDK中其他的锁机制
    private ConcurrentLinkedQueue<Thread> waitList = new ConcurrentLinkedQueue<>();
    @Override
    public void lock() {
        for (;;) {
            if (state.get() == 0)
                if (state.compareAndSet(0, 1))
                    return;
            else{
                    //当一个线程进入这里时把自己放在队列之前 如果这时其他线程释放了锁 这个线程就会错过这次唤醒
                    //错过的如果是最后一次唤醒后这个线程会永远被挂住
                    waitList.add(Thread.currentThread());
                    LockSupport.park();
                    waitList.remove(Thread.currentThread());
                }
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

        Thread t = waitList.peek();

        if (t != null)
            LockSupport.unpark(t);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

class BlockLock implements Lock{
    private AtomicInteger state = new AtomicInteger(0);

    private ConcurrentLinkedQueue<Thread> waitList = new ConcurrentLinkedQueue<>();

    @Override
    public void lock() {
        if (state.compareAndSet(0, 1))
            return;

        waitList.add(Thread.currentThread()); //可能导致一个线程还未调用park方法就调用了unpark方法

        for (;;){
            if (state.get() == 0){
                if (state.compareAndSet(0, 1)){
                    //拿到锁了就可以从队列中删除
                    waitList.remove(Thread.currentThread());
                    return;
                }
            }else
                LockSupport.park();
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

        Thread t = waitList.peek();

        if (t != null)
            LockSupport.unpark(t);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
