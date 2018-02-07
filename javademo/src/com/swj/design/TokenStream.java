package com.swj.design;

import java.io.IOException;

/**
 * @author swj
 * @date 2018/2/7
 */
public interface TokenStream {
    /**
     * 返回当前游标指向的那个token
     * @return
     * @throws IOException
     */
    public Token getToken() throws IOException;

    /**
     * 每次都会使得游标向后移动一位
     */
    public void consumeToken();
}
