package com.shouguouo.base.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/3 - 14:52
 */
public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue(),
            finishingQueue = new CarQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();

        executorService.execute(new EngineRobot(robotPool));
        executorService.execute(new DriveTrainRobot(robotPool));
        executorService.execute(new WheelRobot(robotPool));
        executorService.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        executorService.execute(new Reporter(finishingQueue));

        executorService.execute(new ChassisBuilder(chassisQueue));

        TimeUnit.SECONDS.sleep(7);
        executorService.shutdownNow();
    }
}

class Car {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false;

    public Car(int id) {
        this.id = id;
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void addEngine() {
        engine = true;
    }
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + getId() +
            ", engine=" + engine +
            ", driveTrain=" + driveTrain +
            ", wheels=" + wheels +
            '}';
    }
}

class CarQueue extends LinkedBlockingQueue<Car>{}

class Reporter implements Runnable {

    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Report : " + carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupted!");
        }
        System.out.println("Reporter Off");
    }
}
abstract class Robot implements Runnable {
    private RobotPool robotPool;

    public Robot(RobotPool robotPool) {
        this.robotPool = robotPool;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    public synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        robotPool.release(this);
        while (engage == false)
            wait();
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    abstract protected void performService();

    @Override
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupted!");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this + " off");
    }
}
class EngineRobot extends Robot {
    public EngineRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing engine...");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing DriveTrain...");
        assembler.car().addDriveTrain();
    }
}
class WheelRobot extends Robot {
    public WheelRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing Wheel...");
        assembler.car().addWheels();
    }
}

class RobotPool {
    private Set<Robot> pool = new HashSet<>();
    public synchronized void add(Robot robot) {
        pool.add(robot);
        notifyAll();
    }

    public synchronized void release(Robot r) {
        add(r);
    }

    public synchronized void hire(Class<? extends Robot> clazz, Assembler assembler) throws InterruptedException {
        for (Robot r:
             pool) {
            if (r.getClass().equals(clazz)) {
                pool.remove(r);
                r.assignAssembler(assembler);
                r.engage();
                return;
            }
        }
        wait();
        hire(clazz, assembler);
    }


}
class ChassisBuilder implements Runnable {

    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car car = new Car(counter++);
                System.out.println("ChassisBuilder created " + car);
                carQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting ChassisBuilder Via Interrupted");
        }

        System.out.println("ChassisBuilder off");
    }
}
class Assembler implements Runnable {

    private CarQueue chassisQueue, finishingQueue;

    private Car car;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public CyclicBarrier barrier() {
        return cyclicBarrier;
    }

    public Car car() {
        return car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = chassisQueue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                barrier().await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Assembler Via Interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}