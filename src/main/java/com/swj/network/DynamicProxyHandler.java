package com.swj.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 代理类的具体实现
 * @author swj
 * @date 2018/2/10
 */
public class DynamicProxyHandler implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket s = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", 8080));
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            oos.writeUTF("com.swj.network.rpc.EchoServiceImpl");
            oos.writeUTF(method.getName());
            oos.writeObject(method.getParameterTypes());
            oos.writeObject(args);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
