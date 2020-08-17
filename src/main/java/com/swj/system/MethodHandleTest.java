package com.swj.system;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author shouguouo~
 * @date 2020/8/17 - 19:42
 */
public class MethodHandleTest {
    public static void main(String[] args) throws Throwable {
        MethodType type = MethodType.methodType(int.class, String.class);
        Object sb = MethodHandles.lookup().findStatic(MethodHandleTest.class, "test1", type).invoke("str");
        System.out.println(sb);
        MethodType type2 = MethodType.methodType(int.class, int.class);
        Object sb2 = MethodHandles.lookup().findVirtual(MethodHandleTest.class, "test1", type2).bindTo(new MethodHandleTest()).invoke(2);
        System.out.println(sb2);
        MethodType type3 = MethodType.methodType(String.class);
        Object sb3 = MethodHandles.lookup().findSpecial(Object.class, "toString", type3, MethodHandleTest.class).invoke(new MethodHandleTest());
        System.out.println(sb3);
    }

    public static int test1(String str) {
        System.out.println(str);
        return 1;
    }

    public int test1(int i) {
        return i * i;
    }

    @Override
    public String toString() {
        return hashCode() + "@" + getClass().getName();
    }
}
