package com.swj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author wink~
 * @date 2020/7/2 - 10:50
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int nHorse = 7;
        int pause = 100;
        new HorseRace(nHorse, pause);
    }
}

class HorseRace{
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier;

    public HorseRace(int nHorses, final int pause) {
        cyclicBarrier = new CyclicBarrier(nHorses, () -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                sb.append("=");
            }
            System.out.println(sb);
            for (Horse horse : horses) {
                System.out.println(horse.tracks());
            }
            for (Horse horse:
                 horses) {
                if (horse.getStrides() >= FINISH_LINE) {
                    System.out.println(horse  + "won!");
                    executorService.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("barrier action sleep interrupted");
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse newHorse = new Horse(cyclicBarrier);
            horses.add(newHorse);
            executorService.execute(newHorse);
        }
    }
}

class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;

    private int strides = 0;
    private static Random random = new Random(47);

    private static CyclicBarrier cyclicBarrier;

    public Horse(CyclicBarrier cb) {
        cyclicBarrier = cb;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3); // produce 0.1.2
                }
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("exit");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println("exit");
        }
    }

    @Override
    public String toString() {
        return "Horse{" +
            "id=" + id +
            ", strides=" + strides +
            '}';
    }
    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}