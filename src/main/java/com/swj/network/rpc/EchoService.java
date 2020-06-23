package com.swj.network.rpc;

/**
 * RPC(Remote Procedure Call) 远程调用接口
 * 客户端向服务端发起调用所使用的接口
 * @author swj
 * @date 2018/2/10
 */
public interface EchoService {
    /**
     *  远程调用方法
     * @param request
     * @return
     */
    String echo(String request);
}
