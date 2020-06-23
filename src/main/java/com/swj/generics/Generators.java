package com.swj.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author wink~
 * @date 2020/3/20 - 13:55
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<String> coll = fill(new ArrayList<>(), new Generator<>(String::new), 10);
        coll.forEach(System.out::println);
    }
}

class Generator<T> {
    public final Supplier<T> supplier;
    Generator(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    T next() {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
