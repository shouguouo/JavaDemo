package com.shouguouo.base.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author shouguouo~
 * @date 2020/7/17 - 23:47
 */
public class CommandLineTest {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c dir");
        InputStream input = process.getInputStream();
        BufferedReader bufferInput = new BufferedReader(
            new InputStreamReader(input, Charset.forName("gbk")));
        bufferInput.lines().forEach(System.out::println);
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.totalMemory());

    }
}
