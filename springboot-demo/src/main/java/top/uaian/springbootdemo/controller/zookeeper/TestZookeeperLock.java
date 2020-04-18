package top.uaian.springbootdemo.controller.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.uaian.tool.utils.Zookeeper.ZookeeperDistributedLock;

@RestController
@RequestMapping("/test/zk/lock")
public class TestZookeeperLock {

    @Value("${zk.host}")
    public String CONNECT_STRING;

    @RequestMapping("/fair")
    public void fair(){
        ZookeeperDistributedLock zookeeperDistributedLock = ZookeeperDistributedLock.create(CONNECT_STRING,"testLock"
                , true);
        try {
            zookeeperDistributedLock.lock();
            Thread.sleep(1000);
            zookeeperDistributedLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/unfair")
    public void unfair(){
        ZookeeperDistributedLock zookeeperDistributedLock = ZookeeperDistributedLock.create(CONNECT_STRING, "testLock"
                , false);
        try {
            zookeeperDistributedLock.lock();
            Thread.sleep(1000);
            zookeeperDistributedLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
