package com.shouguouo.base.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author shouguouo~
 * @date 2020/6/30 - 9:56
 */
public class NIOInterruption {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future<?> f = executorService.submit(new NIOBlocked(sc1));
        executorService.execute(new NIOBlocked(sc2));
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true); // caught ClosedByInterruptException
        TimeUnit.SECONDS.sleep(1);
        sc2.close(); // caught AsynchronousCloseException

        executorService = Executors.newCachedThreadPool();
        Future<?> f2 = executorService.submit(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        f2.cancel(true); // can not interrupted
        executorService.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.in.close(); // may not useful
    }
}

class NIOBlocked implements Runnable{
    private final SocketChannel socketChannel;

    public NIOBlocked(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for reading " + this);
            socketChannel.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException cie) {
            System.out.println("ClosedByInterruptException");
        } catch (AsynchronousCloseException ace) {
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("exiting NIOBlocked.run() " + this);
    }
}

class IOBlocked implements Runnable {

    private final InputStream is;

    public IOBlocked(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for reading " + this);
            is.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("exiting IOBlocked.run() " + this);
    }
}
