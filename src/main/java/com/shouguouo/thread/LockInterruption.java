package com.shouguouo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shouguouo~
 * @date 2020/6/30 - 11:00
 */
public class LockInterruption {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> f = executorService.submit(new Blocked());
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        executorService.shutdown();
    }
}

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f(){
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("interrupted from lock acquisition in f()");
        }
    }
}

class Blocked implements Runnable {
    BlockedMutex blockedMutex = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("waiting for f() in BlockedMutex");
        blockedMutex.f();
        System.out.println("broken out of the blocked call");
    }
}
