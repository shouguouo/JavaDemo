package com.shouguouo.base.thread;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/7/8 - 19:32
 */
public class Memorizer<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public Memorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> computable.compute(arg);
                FutureTask<V> task = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, task);
                if (f == null) {
                    f = task;
                    task.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable c) {
        if (c instanceof RuntimeException) {
            return (RuntimeException) c;
        } else if (c instanceof Error) {
            throw (Error) c;
        } else {
            throw new IllegalStateException("Not unchecked", c);
        }
    }
}

interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new BigInteger(arg);
    }

}
