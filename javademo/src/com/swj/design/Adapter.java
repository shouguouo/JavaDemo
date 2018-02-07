package com.swj.design;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 设配器模式
 * @author swj
 * @date 2018/2/7
 */
public class Adapter {
    /**
     * 这里 InputStreamReader就是适配器 具有把字节码转成字符的能力
     * java.io中类学完常见的设计模式就能猜到有哪些类了
     * InputStreamReader和OutputStreamWriter是连通字节与字符的桥梁
     * @param args
     */
    public static void main(String[] args) {
        char[] buf = new char[256];
        System.out.println("hey, may I have your name, please? ");
        int n = 0;
        Reader r = new InputStreamReader(System.in);
        try {
            n = r.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hello, Mr." + buf[0]);
    }
}
