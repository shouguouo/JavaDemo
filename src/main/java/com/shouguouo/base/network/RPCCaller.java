package com.shouguouo.base.network;

import com.shouguouo.base.network.rpc.EchoService;

import java.lang.reflect.Proxy;

/**
 * RPC客户端 只知道接口 采用动态代理
 * @author swj
 * @date 2018/2/10
 */
public class RPCCaller {
    public static void main(String[] args) {
        EchoService echoService = (EchoService) Proxy
                .newProxyInstance(EchoService.class.getClassLoader(), new Class<?>[]{EchoService.class}, new DynamicProxyHandler());
        System.out.println(echoService.echo("swj"));
    }
}
