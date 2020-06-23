package com.swj.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java中的中断机制
 * Java没有提供一种安全直接的方法来停止某个线程 但是提供了中断机制
 * 如果对java中断没有一个全面的了解 可能误以为被中断的线程将立马退出运行 但事实并非如此
 *
 * @author swj
 * @date 2018/2/27
 */
public class InterruptMechanism {
    /**
     * 中断的原理
     * 中断机制是一种协作机制 就是说通过中断并不能直接终止另一个线程 而需要被中断的线程自己处理中断
     * 每个线程对象里都有一个boolean类型的标识(不一定就是Thread类的字段 实际上也确实不是 这几个方法都是通过native方法完成的) 代表着是否有中断请求
     * Thread类中提供了几个方法来操作这个中断状态
     * public static boolean interrupted 测试当前线程是否已经中断。线程的中断状态由该方法清除
     * 换句话说，如果连续两次调用该方法，则第二次调用将返回 false
     * 在第一次调用已清除了其中断状态之后，且第二次调用检验完中断状态前，当前线程再次中断的情况除外）
     * public boolean isInterrupted() 测试线程是否已经中断 线程的中断状态不受该方法的影响
     * public void interrupt()中断线程 唯一将中断状态设置为true的方法
     */

    /**
     * 中断的处理
     * 处理时机：作为一种协作机制，不会强求被中断线程一定要在某个点进行处理。实际上，被中断线程只需在合适的时候处理即可，
     * 如果没有合适的时间点，甚至可以不处理，这时候在任务处理层面，就跟没有调用中断方法一样。
     * 处理时机决定着程序的效率与中断响应的灵敏性。频繁的检查中断状态可能会使程序执行效率下降，
     * 相反，检查的较少可能使中断请求得不到及时响应。如果发出中断请求之后，被中断的线程继续执行一段时间不会给系统带来灾难，
     * 那么就可以将中断处理放到方便检查中断，同时又能从一定程度上保证响应灵敏度的地方。当程序的性能指标比较关键时，
     * 可能需要建立一个测试模型来分析最佳的中断检测点，以平衡性能和响应灵敏性。
     */
    /**
     * 磁盘文件扫描
     * @param f
     * @throws InterruptedException
     */
    private static void listFile(File f) throws InterruptedException {
        if (f == null){
            throw new IllegalArgumentException();
        }
        if (f.isFile()){
            System.out.println(f);
            return;
        }
        File[] allFiles = f.listFiles();
        if (Thread.interrupted()){
            throw new InterruptedException("文件扫描任务被中断");
        }
        for (File file: allFiles){
            listFile(file);
        }
    }

    public static String readFromConsole(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args) {
        final Thread fileIteratorThread = new Thread(){
            @Override
            public void run() {
                try {
                    listFile(new File("E:\\"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(){
            @Override
            public void run() {
                while (true){
                    if ("quit".equalsIgnoreCase(readFromConsole())){
                        if (fileIteratorThread.isAlive()){
                            fileIteratorThread.interrupt();
                            return;
                        }
                    }else {
                        System.out.println("输入quit退出文件扫描");
                    }
                }
            }
        }.start();
        fileIteratorThread.start();
    }
}