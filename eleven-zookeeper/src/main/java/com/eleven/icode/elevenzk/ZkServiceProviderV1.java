package com.eleven.icode.elevenzk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author by Eleven on 2021/12/20
 */
public class ZkServiceProviderV1 implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static String rootPath = "/GroupMembers";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper("localhost:2181", 5000, new ZkServiceProviderV1());
        countDownLatch.await();
        zk.create(rootPath + "/c1", "test carete".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("创建集群节点c1：" + rootPath + "c1");
        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && event.getPath() == null) {
                countDownLatch.countDown();
            }
        }
    }
}
