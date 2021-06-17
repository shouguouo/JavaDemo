package com.shouguouo.network.rpc;

/**
 * 服务端实现类
 * @author swj
 * @date 2018/2/10
 */
public class EchoServiceImpl implements EchoService{

    @Override
    public String echo(String request) {
        return "echo : " + request;
    }
}
