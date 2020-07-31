package com.swj.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wink~
 * @date 2020/7/31 - 17:28
 */
public class PhantomReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        PhantomReference<byte[]> reference = new PhantomReference<>(new byte[1], queue);
        PhantomReference<byte[]> reference1 = new PhantomReference<>(new byte[2], queue);
        PhantomReference<byte[]> reference2 = new PhantomReference<>(new byte[3], queue);
        new Thread(() -> {
            while (true) {
                Reference<? extends byte[]> poll = queue.poll();
                if (poll == reference) {
                    System.out.println("0");
                }
                if (poll == reference1) {
                    System.out.println("1");
                }
                if (poll == reference2) {
                    System.out.println("2");
                }
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
        System.gc();
    }
}
