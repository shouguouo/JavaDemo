package com.swj.generics;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class Tuple {
    public static void main(String[] args) {
        f("", new StringBuffer(), new StringBuilder());
    }

    public static <T, Y> void f(T t, StringBuffer s, Y y) {
        System.out.println(t.getClass().getCanonicalName());
        System.out.println(s.getClass().getCanonicalName());
        System.out.println(y.getClass().getCanonicalName());
    }
}

class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
