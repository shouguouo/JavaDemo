package com.shouguouo.cache;

import com.google.common.base.Stopwatch;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo
 * @date 2021-06-18 09:26:39
 */
public class GuavaCacheTry {

    public static final Cache<Thread, List<User>> CACHE = CacheBuilder
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            List<User> userList = query();
            System.out.println(userList.hashCode());
            System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
            stopwatch = Stopwatch.createStarted();
            userList = query();
            System.out.println(userList.hashCode());
            System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
            CACHE.invalidate(Thread.currentThread());
            stopwatch = Stopwatch.createStarted();
            userList = query();
            System.out.println(userList.hashCode());
            System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (cause instanceof IOException) {
                    throw (IOException) cause;
                }
                throw new RuntimeException(cause);
            }
            throw new RuntimeException(e);
        }
    }

    public static List<User> query() throws ExecutionException {
        return CACHE.get(Thread.currentThread(), () -> {
            TimeUnit.SECONDS.sleep(2);
            return Lists.newArrayList(new User("swj", 24), new User("xhy", 24));
        });
    }
}
