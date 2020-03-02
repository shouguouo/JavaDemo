package com.swj.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 互斥
 * 结果大概率落在（30300，30450）
 * TestMutex 典型的并发 可以把add操作用一个atomic变量保护起来 线程只有获得许可 才能进行atomic操作 没有许可直接放弃
 * 改造后的AtomicMutex
 * Created by swj on 2018/1/19.
 */
public class Mutex {

    public static void main(String[] args) {
        //TestMutex test = new TestMutex();
        AtomicMutex test = new AtomicMutex();
        int THREAD_NUM = 10;
        Thread[] threads = new Thread[THREAD_NUM];

        //创建10个线程 让他们不断去增加test.sum的值
        for (int i = 0; i < THREAD_NUM; i++){
            Thread t = new Thread(){
                public void run(){
                    for (int j = 0; j < 10000; j++){
                        test.add();
                    }
                }
            };
            t.start();
            threads[ i ] = t;
        }

        for (Thread t : threads){
            try {
                t.join(); //join方法:等待线程终止 也就是说子线程调用了join方法 后面的代码只有等到子线程执行完才能执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(test.sum);

    }
}
class TestMutex{
    public int sum = 0;

    public void add(){
        if (sum < 30000){
            try{
                //这里sleep一下，增加线程切换的概率
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += 50;
        }
    }
}

class AtomicMutex{
    public int sum = 0;
    AtomicInteger mutex = new AtomicInteger(0);

    public void add(){
        if (!mutex.compareAndSet(0, 1))
            return;

        if (sum < 30000){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += 50;
        }
        mutex.set(0);
    }
}