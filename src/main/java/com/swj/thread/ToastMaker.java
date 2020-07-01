package com.swj.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wink~
 * @date 2020/7/1 - 16:48
 */
public class ToastMaker {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
            butteredQueue = new ToastQueue(),
            jammedQueue = new ToastQueue();
        Toaster toaster = new Toaster(dryQueue);
        Butter butter = new Butter(dryQueue, butteredQueue);
        Jam jam = new Jam(butteredQueue, jammedQueue);
        Eater eater = new Eater(jammedQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(toaster);
        executorService.execute(butter);
        executorService.execute(jam);
        executorService.execute(eater);
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}

class Toast{
    public enum Status {DRY, BUTTERED, JAMMED;}
    private Status status = Status.DRY;
    private final int id;
    public Toast(int id) {
        this.id = id;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast：" + id + " = " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
    private ToastQueue toastQueue;

    private int count;

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100);
                toastQueue.put(new Toast(++count));
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster Interrupted");
        }
    }
}

class Butter implements Runnable {
    private ToastQueue dryQueue;

    private ToastQueue butteredQueue;

    public Butter(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = dryQueue.take();
                toast.butter();
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Butter Interrupted");
        }
    }
}

class Jam implements Runnable {
    private ToastQueue butteredQueue;
    private ToastQueue jammedQueue;

    public Jam(ToastQueue butteredQueue, ToastQueue jammedQueue) {
        this.butteredQueue = butteredQueue;
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = butteredQueue.take();
                toast.jam();
                jammedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Jam Interrupted");
        }
    }
}

class Eater implements Runnable {

    private ToastQueue jammedQueue;

    private int counter;

    public Eater(ToastQueue jammedQueue) {
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = jammedQueue.take();
                if (toast.getId() != ++counter || toast.getStatus() != Toast.Status.JAMMED) {
                    System.out.println("error: " + toast);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + toast);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater Interrupted");
        }
    }
}