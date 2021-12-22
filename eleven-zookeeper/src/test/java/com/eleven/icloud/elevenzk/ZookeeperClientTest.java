package com.eleven.icloud.elevenzk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author by YingLong on 2021/11/22
 */
@Slf4j
public class ZookeeperClientTest {

    private static final String ZK_ADDRESS = "localhost:2181";

    private static final int SESSION_TIMEOUT = 5000;

    private static ZooKeeper zooKeeper;

    private static final String ZK_NODE = "/zk-node";

    /**
     * @param connectString           - ZooKeeper服务器列表，由英文逗号分开的host:port字符串组成，
     *                                每一个都代表一台ZooKeeper机器，如host1:port1,host2:port2,host3:port3。也可在connectString中设置客户端连接上ZooKeeper
     *                                后的根目录，方法是在host:port字符串之后添加该根目录，如host1:port1,host2:port2,host3:port3/zk-base,这样就指定了该客户端连接上ZooKeeper服务器之后,所有对ZooKeeper
     *                                的操作，都会基于该根目录。如客户端对/sub-node的操作，最终创建/zk-node/sub-node, 该目录也叫Chroot，即客户端隔离命名空间。
     * @param sessionTimeout          - 会话超时时间，以毫秒为单位的整型值。在ZooKeeper中有会话概念，在一个会话周期内，ZooKeeper客户端和服务器之间会通过心跳检
     *                                测机制来维持会话的有效性，一旦在sessionTimeout时间内没有进行有效的心跳检测，会话将失效。
     * @param watcher                 - ZooKeeper允许客户端在构造方法中传入一个接口Watcher(org.apache.zookeeper.Watcher)的实现类对象来作为默认Watcher事件通知处理器。
     *                                该参数可设置为null，表明不需要设置默认Watcher处理器。
     * @param canBeReadOnly           -  boolean类型参数，用于标识当前会话是否支持read-only只读模式。默认在ZooKeeper集群中，一个机器若和集群中过半及
     *                                以上机器失去了网络连接，则该机器将不再处理客户端请求，包括读写请求。但在某些使用场景下，当ZooKeeper服务器发生此类故障时，
     *                                希望ZooKeeper服务器能够提供读服务，当然写服务肯定无法提供，这就是ZooKeeper read-only模式。
     * @param sessionId和sessionPasswd - 分别代表会话ID和会话秘钥，唯一确定一个会话，客户端使用这两个参数可以实现客户端会话复用，从而达到恢复会话的效果
     *                                第一次连接上ZooKeeper服务器时，通过调用ZooKeeper对象实例的以下两个接口，即可获得当前会话的ID和秘钥
     *                                long getSessionId(); byte[]getSessionPasswd( ); 荻取到这两个参数值后，可在下次创建ZooKeeper对象实例时传入构造方法
     */
    @Before
    public void init() throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected &&
                    event.getType() == Watcher.Event.EventType.None) {
                countDownLatch.countDown();
                log.info("连接成功！");
            }
        });
        log.info("连接中....");
        countDownLatch.await();
    }

    /**
     * 同步创建节点
     */
    @Test
    public void createTest() throws KeeperException, InterruptedException {
        String path = zooKeeper.create(ZK_NODE, "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("created path: {}", path);
    }

    /**
     * 异步创建节点
     */
    @Test
    public void createAsycTest() throws InterruptedException {
        zooKeeper.create(ZK_NODE, "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
                (rc, path, ctx, name) -> log.info("rc  {},path {},ctx {},name {}", rc, path, ctx, name), "context");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    /**
     * 修改节点数据
     */
    @Test
    public void setTest() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改前: {}", new String(data));
        zooKeeper.setData(ZK_NODE, "changed!".getBytes(), stat.getVersion());
        byte[] dataAfter = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改后: {}", new String(dataAfter));
    }
}
