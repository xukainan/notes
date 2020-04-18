package top.uaian.tool.utils.Zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class FairLock extends ZookeeperDistributedLock{

    private Logger logger = LoggerFactory.getLogger(FairLock.class);

    //锁的名称
    private String lockName;
    //当前线程创建的序列node
    private ThreadLocal<String> nodeId = new ThreadLocal<>();

    public FairLock(String servers, String lockName) {
        super(servers);
        this.lockName = lockName;
    }

    @Override
    public void lock() throws Exception {
        try{
            //创建临时序列子节点
            String node_epersistent_sequential = zooKeeper.create(root + "/" + lockName, data,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info(Thread.currentThread().getName() +"_"+ node_epersistent_sequential + ":Created" );
            //取出所有子节点
            List<String> allNodes = zooKeeper.getChildren(root, false);
            TreeSet<String> sortedNodes = new TreeSet<>();
            allNodes.stream().forEach(node -> {
                sortedNodes.add(root + "/" + node);
            });
            //最小，即第一个创建的
            String smallNode = sortedNodes.first();
            //前一个
            String preNode = sortedNodes.lower(node_epersistent_sequential);
            if(node_epersistent_sequential.equals(smallNode)) {
                logger.info(Thread.currentThread().getName() +"_"+ node_epersistent_sequential + ":get lock");
                nodeId.set(node_epersistent_sequential);
                return;
            }

            CountDownLatch latch = new CountDownLatch(1);
            Stat stat = zooKeeper.exists(preNode, new LockWatcher(latch));
            // 判断比自己小一个数的节点是否存在,如果存在等待锁,同时注册监听
            if(stat != null) {
                logger.info(Thread.currentThread().getName() +"_"+ node_epersistent_sequential + ":waiting for " + preNode + " released lock");
                latch.wait();
                nodeId.set(node_epersistent_sequential);
                latch = null;
            }



        }catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void unlock() {
        try{
            logger.info(Thread.currentThread().getName() +"_"+ nodeId.get() + ":unlock");
            if(null != nodeId) {
                zooKeeper.delete(nodeId.get(), -1);
            }
            nodeId.remove();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
