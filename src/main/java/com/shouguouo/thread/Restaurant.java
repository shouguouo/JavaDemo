package com.shouguouo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shouguouo~
 * @date 2020/7/1 - 15:58
 */
public class Restaurant {
    Meal meal;
    Chef chef = new Chef(this);
    WaitingPerson waitingPerson = new WaitingPerson(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() {
        executorService.execute(chef);
        executorService.execute(waitingPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

class Meal{
    private final int orderNumber;

    public Meal(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Meal{" +
            "orderNumber=" + orderNumber +
            '}';
    }
}

class WaitingPerson implements Runnable{

    private Restaurant restaurant;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public WaitingPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal == null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("WaitingPerson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try{
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }

            }
        } catch (InterruptedException e) {
            System.out.println("WaitingPerson interrupted");
        }
    }
}

class Chef implements Runnable{

    private Restaurant restaurant;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal != null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.executorService.shutdownNow();
                }
                System.out.print("Order up");
                restaurant.waitingPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitingPerson.condition.signalAll();
                } finally {
                    restaurant.waitingPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }

    }
}
