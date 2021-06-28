package com.shouguouo.base.gc;

/**
 * -XX:+PrintGCDetails
 * @author shouguouo~
 * @date 2020/7/31 - 16:38
 */
public class AllocateObjTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30000 * 2 * 1024];
        allocation2 = new byte[1000 * 1024];
    }
}
