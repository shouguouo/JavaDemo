package com.shouguouo.base.thread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * java8中的CompletableFuture进行异步编程
 *  @author shouwj
 *  @date 2018/1/30
 */
public class Async {

    public static Integer doSomething(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
    /**
     * 使用线程池和future来实现异步编程演示
     * 对稍微复杂的异步处理束手无策
     * 比如等待future集合中所有任务完成...
     */
    public static void useFutureAndThreadPool(){
        ExecutorService pool = Executors.newCachedThreadPool();
        //构造future结果 这里doSomething()十分耗时 采用异步
        Future<Integer> future = pool.submit(() -> doSomething());
        //做一些别的事情
       //doSomething();
        try {
            //获取future结果 设置超时时间
            Integer result = future.get(5, TimeUnit.SECONDS);
            //打印结果
            System.out.printf("the async result is %d", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("任务计算抛出异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("线程在等待中被中断");
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("future对象等待时间超时");
        }
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }

    private static Random random = new Random();
    private static double calculatePrice(String product){
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原生态写法 CompletableFuture来异常执行任务
     * @param product
     * @return
     */
    public static Future<Double> getAsyncPrice(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //将getPrice使用completeFuture转换为异步方法
        new Thread(() -> {
            //使用completeExceptionally来得到异常信息并且结束这次异步任务
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    /**
     * CompletableFuture本身提供了大量的工厂方法来供我们方便的实现一个异步编程
     * supplyAsync接受一次Supplier作为参数 返回一个CompletableFuture对象
     * @param product
     * @return
     */
    public static Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    //使用流异步操作同步API

    /**
     *  有一个需求 接受产品名做参数 返回一个字符串列表 包括商店的名称 商品的价格
     */
    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavouriteShop"),
            new Shop("BuyItAll"));

    /**
     * 同步方式
     * @param product
     * @return
     */
    public List<String> findPrices(String product){
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 异步
     * @param product
     * @return
     */
    public List<String> findPricesAsync(String product){
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        //join 方法本身是阻塞的 完成收集 而不采用shop->completableFuture->join->collect 会使整个操作变回阻塞的
        return priceFutures.stream()
                .map(CompletableFuture :: join)
                .collect(Collectors.toList());
    }

    /**
     * 进阶的异步流操作
     * 同步任务 future.thenApply(Function)
     * 异步任务 future.thenCompose(Function)
     * 将两个CompletableFuture对象整个在一起 无论是否存在依赖 thenCombine
     * 对结果进行处理 thenAccept(Consumer)
     * 对流对象进行类似及早求值的操作 allOf和anyOf
     * allOf 需等待所有的CompletableFuture对象执行完毕
     * anyOf 返回第一个执行完毕的CompletableFuture对象
     */

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        //useFutureAndThreadPool();
        //System.out.println(getAsyncPrice("apple").get(10, TimeUnit.SECONDS));
        System.out.println(getPriceAsync("apple").get());
        System.out.println(new Async().findPrices("apple"));
        System.out.println(new Async().findPricesAsync("apple"));
    }
}

class Shop{
    private String name;
    private double price;
    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice(String product){
        return product.charAt(0);
    }
}
