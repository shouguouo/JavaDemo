package com.swj.generics;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class Tuple {
    public static void main(String[] args) {

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
