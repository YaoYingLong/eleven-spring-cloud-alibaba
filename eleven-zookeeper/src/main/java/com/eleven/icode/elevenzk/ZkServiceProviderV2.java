package com.eleven.icode.elevenzk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author by Eleven on 2021/12/20
 */
public class ZkServiceProviderV2 implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();
    private static String rootPath = "/GroupMembers";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/username";
        zk = new ZooKeeper("localhost:2181", 5000, new ZkServiceProviderV2());
        countDownLatch.await();
        String data = new String(zk.getData(path, true, stat));
        System.out.println("获取节点数据：" + data);
        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent event) {
        if (Event.EventType.None == event.getType() && event.getPath() == null) {
            countDownLatch.countDown();
        } else if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                String data = new String(zk.getData(event.getPath(), true, stat));
                System.out.println("节点数据更新：" + data);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
