package com.shouguouo.design;

import java.io.IOException;
import java.io.Reader;

/**
 * @author swj
 * @date 2018/2/7
 */
public class MyTokenStream extends Reader{

    public MyTokenStream() {
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
