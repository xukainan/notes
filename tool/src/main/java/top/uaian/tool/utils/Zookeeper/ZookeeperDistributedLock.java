package top.uaian.tool.utils.Zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
//@Component
//@PropertySource({"classpath:config/zookeeper.properties"})
public class ZookeeperDistributedLock {

    private Logger logger = LoggerFactory.getLogger(ZookeeperDistributedLock.class);

    protected ZooKeeper zooKeeper;
    // 会话超时时间：毫秒
    protected final static int sessionTimeout = 50000;
    // zk是一个目录结构，root为分布式锁最外层目录
    protected String root = "/locks";
    // 用来同步等待zkclient链接到了服务端
    protected CountDownLatch connectedSignal = new CountDownLatch(1);
    // 节点数据：无需数据
    protected final static byte[] data = new byte[0];
    // todo zk地址, 不同模块注入不成功，后续解决
//    @Value("${zk.host}")
//    public String CONNECT_STRING;

    private String config;

    private String lockName;

    public static ZookeeperDistributedLock create(String servers, String lockName, boolean isFair){
        return isFair?new FairLock(servers, lockName): new UnFairLock(servers, lockName);
    }

    //构造方法，建立zk链接
    protected ZookeeperDistributedLock(String servers) {
        try {
            zooKeeper = new ZooKeeper(servers, sessionTimeout,new Watcher(){
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //建立连接
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });
            connectedSignal.await();
            Stat stat = zooKeeper.exists(root, false);
            if(null == stat) {
                zooKeeper.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * 锁
     * @throws Exception
     */
    public void lock() throws Exception {
        // 具体子类实现
        throw new UnsupportedOperationException("不支持的操作！");
    }

    /**
     * 解锁
     */
    public void unlock() {
        // 具体子类实现
        throw new UnsupportedOperationException("不支持的操作！");
    }

}
