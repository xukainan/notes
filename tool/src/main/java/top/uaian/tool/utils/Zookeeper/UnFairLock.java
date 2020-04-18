package top.uaian.tool.utils.Zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class UnFairLock extends ZookeeperDistributedLock{

    private Logger logger = LoggerFactory.getLogger(UnFairLock.class);

    //锁的全路径
    private String lockPath;

    public UnFairLock(String servers, String lockName) {
        super(servers);
        lockPath = root + "/" + Objects.requireNonNull(lockName);
    }

    @Override
    public void lock() throws Exception {
        try {
            zooKeeper.create(lockPath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info(Thread.currentThread().getName() + ":get lock" );
            return;
        }catch (KeeperException.NodeExistsException existEx) {
            //节点已经存在，说明锁被占用
            CountDownLatch latch = new CountDownLatch(1);
            Stat stat = zooKeeper.exists(lockPath, new LockWatcher(latch));
            if(stat != null) {
                logger.info(Thread.currentThread().getName() + ":waiting for " + lockPath + " released lock" );
                latch.await();
                latch = null;
            }
        }catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void unlock() {
        try {
            logger.info(Thread.currentThread().getName() + ":unlock" );
            zooKeeper.delete(lockPath, -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
