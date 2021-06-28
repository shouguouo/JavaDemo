package com.shouguouo.base.aop;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author shouguouo~
 * @date 2020/3/21 - 23:19
 */
public class GenerateProxyClass {

    public static void main(String[] args) {
        byte[] clazz = ProxyGenerator.generateProxyClass("UserInterface$Proxy0", new Class[] {UserInterface.class});
        File file = new File("D:\\UserInterface$Proxy0.class");

        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(clazz);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
