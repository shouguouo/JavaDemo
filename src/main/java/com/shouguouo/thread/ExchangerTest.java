package com.shouguouo.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger提供一种基于交换对象的线程协作模式。
 * @author shouguouo~
 * @date 2020/7/3 - 13:33
 */
public class ExchangerTest {
    static final int SIZE = 1;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Fat> consumerFatList = new CopyOnWriteArrayList<>(),
            producerFatList = new CopyOnWriteArrayList<>();
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        executorService.execute(new FatProducer(producerFatList,exchanger, SIZE));
        executorService.execute(new FatConsumer(consumerFatList,exchanger));
        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();
    }
}

class FatProducer implements Runnable {

    private List<Fat> fatList;
    private Exchanger<List<Fat>> exchanger;
    private final int size;

    private int count = 0;
    public FatProducer(List<Fat> fatList, Exchanger<List<Fat>> exchanger, int size) {
        this.fatList = fatList;
        this.exchanger = exchanger;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < size; i++) {
                    Fat fat = new Fat();
                    fatList.add(fat);
                    System.out.println("produce " + fat + " No:" + ++count);
                }
                fatList = exchanger.exchange(fatList);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer Interrupted");
        }
    }
}
class FatConsumer implements Runnable {

    private List<Fat> fatList;
    private Exchanger<List<Fat>> exchanger;

    private int count = 0;
    public FatConsumer(List<Fat> fatList, Exchanger<List<Fat>> exchanger) {
        this.fatList = fatList;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (Fat fat:
                     fatList) {
                    fatList.remove(fat);
                    System.out.println("consume " + fat + " No:" + ++count);
                }
                fatList = exchanger.exchange(fatList);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer Interrupted");
        }
    }
}
