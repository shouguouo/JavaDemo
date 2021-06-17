package com.shouguouo.thread;

/**
 * @author shouguouo~
 * @date 2020/6/9 - 21:58
 */
public class LockTest {
    // LockTest.class 类锁
    public synchronized static void classLock() {

    }
    // new LockTest() 对象锁
    public synchronized void thisLock() {

    }

}
