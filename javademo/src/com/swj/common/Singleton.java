package com.swj.common;

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