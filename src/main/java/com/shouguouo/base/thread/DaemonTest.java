package com.shouguouo.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/6/26 - 14:39
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Daemon());
        t.setDaemon(true);
        t.start();
    }

    static class Daemon implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("starting daemon...");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("This is finally");
            }
        }
    }
}
