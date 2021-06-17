package com.shouguouo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS 实现单例模式 真正意义上的无锁实现
 * 其他都是借助JVM的ClassLoader类加载初始化保证并发安全的机制 底层也是使用synchronized 或 lock的机制
 * Created by swj on 2018/1/25.
 */
public class Singleton {
    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<>();
    private Singleton(){}

    public static Singleton getInstance(){
        for (;;){
            Singleton singleton = INSTANCE.get();
            if (null != singleton){
                return singleton;
            }
            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null, singleton)){
                return singleton;
            }
        }
    }

    // volatile保证new Singleton()时指令重排（分配空间、初始化对象、设置引用） 导致singleton未实例化完成时，其他线程以为实例化已经完成直接返回未初始化完成的singleton
    private static volatile Singleton singleton;

    public static Singleton getSingleton() {
        if (singleton == null) { // 保证性能
            synchronized (Singleton.class) {
                if (singleton == null) { // 获取锁后再进行判断，防止多次实例化
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
    protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) throws ParseException {
        LocalDate sb = LocalDate.now();
        LocalDate source = LocalDate.parse("20190828", DateTimeFormatter.ofPattern("yyyyMMdd"));

        Calendar sd = Calendar.getInstance();
        Calendar ed = Calendar.getInstance();

        sd.setTime(sdf.parse("20190821"));
        ed.setTime(sdf.parse("20190921"));

        ed.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(sd.getTime());
        System.out.println(ed.getTime());
        for (; sd.before(ed);) {
            System.out.println(sdf.format(sd.getTime()));
            sd.add(Calendar.DAY_OF_MONTH, 1);
        }
        Singleton aa = SingletonEnum2.INSTNACE.getInstance();
        Singleton bb = SingletonEnum2.INSTNACE.getInstance();
        System.out.println(aa == bb);
    }
}

/**
 * enum类型实现单例在有些情况下是最好的方式 Java规范保证了每一个枚举类型及其定义的枚举变量在JVM中都是唯一的
 * 1.线程安全 2.不会因为序列化产生多个实例 3.防止反射攻击
 * 1.通过类加载机制保证 无需考虑Double checked locking
 * 2.枚举类自己实现了readResolved()方法 抗序列化
 * 3.枚举类怎么防止反射攻击？编译后的enum类为abstract的类型
 */
enum SingletonEnum{
    INSTANCE{
        @Override
        protected void read(){
            System.out.println("read");
        }
        @Override
        protected void write(){
            System.out.println("write");
        }
    };

    protected abstract void read();
    protected abstract void write();
}

enum SingletonEnum2{
    INSTNACE;
    private Singleton singleton = Singleton.getSingleton();

    public Singleton getInstance() {
        return singleton;
    }

}