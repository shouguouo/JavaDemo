package com.swj.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author wink~
 * @date 2020/6/25 - 16:21
 */
public class VolatileVisibilityTest {

    private volatile boolean flag = true;

    public void loop(){
        System.out.println("Enter loop");
        while (this.flag) {

        }
        System.out.println("Exit loop");
    }

    public void reverseFlag() {
        this.flag = !this.flag;
    }

    public static void main(String[] args) {
        VolatileVisibilityTest vo = new VolatileVisibilityTest();
        new Thread(vo::loop).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(vo::reverseFlag).start();
    }
}
