package com.shouguouo.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author shouguouo~
 * @date 2020/5/8 - 20:20
 */
public class ZkTest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper cli = new ZooKeeper("10.20.27.199:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        });
        for (String subPath :  cli.getChildren("/", false)) {
            printPathRecursive(cli, "/" + subPath);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void printPathRecursive(ZooKeeper cli, String path) throws KeeperException, InterruptedException {
        byte[] data = cli.getData(path, false, null);
        System.out.println(new String(data));
        for (String subPath :  cli.getChildren(path, false)) {
            printPathRecursive(cli, path + "/" + subPath);
        }
    }

}
