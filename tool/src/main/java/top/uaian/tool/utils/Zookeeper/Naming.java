package top.uaian.tool.utils.Zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * description:  使用zk实现全局统一命名服务<br>
 * date: 2020/4/20 9:23 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
public class Naming {
    private Logger logger = LoggerFactory.getLogger(Naming.class);
    //根节点
    private String root = "/NameService";
    //客户端
    private ZooKeeper zooKeeper;
    //连接超时
    private int sessionTimeout = 10000;
    //空数据
    private byte[] bytes = new byte[]{};
    //用来同步等待zkclient链接到了服务端
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public Naming(String config) throws IOException {
        try {
            zooKeeper = new ZooKeeper(config, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            Stat stat = zooKeeper.exists(root, false);
            if(stat == null) {
                zooKeeper.create(root, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public void unNaming() throws InterruptedException {
        if(zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public void register(String node){
        try {
            Stat exists = zooKeeper.exists(root + node, false);
            if(exists == null) {
                zooKeeper.create(root + node, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                logger.info(node +":created");
            } else {
                logger.info(node +":existed");
            }

        }catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public void unRegister(String node) {
        try {
            Stat exists = zooKeeper.exists(root + node, false);
            if(exists == null) {
                logger.info(node +":is null");
            } else {
                zooKeeper.delete(root + node, -1);
                logger.info(node +":existed");
            }

        }catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
