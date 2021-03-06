package com.shouguouo.base.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/9 - 21:52
 */
public class OutOfTime {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 5);
        TimeUnit.SECONDS.sleep(5);
    }
}

class ThrowTask extends TimerTask {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}