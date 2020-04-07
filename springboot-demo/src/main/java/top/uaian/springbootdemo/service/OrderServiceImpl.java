package top.uaian.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.uaian.tool.utils.RedisLockUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * description:  <br>
 * date: 2020/4/7 10:37 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    RedisLockUtils redisLockUtils;

    /**
     * 活动产品 限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表,商品信息表,库存表,秒杀成功订单表
         */
        products=new HashMap<>();
        stock=new HashMap<>();
        orders=new HashMap<>();
        products.put("123457",100000);
        stock.put("123457",100000);
    }


    @Override
//    public synchronized boolean addOrder(String productId) {
    public boolean addOrder(String productId) {
        //查询该商品库存,为0则活动结束.
        //加锁
        long time=System.currentTimeMillis();

        if (!redisLockUtils.lock(productId)){
            System.out.println("人也太多了,换个姿势再试试~");
            return false;
        }

        int stockNum=stock.get(productId);
        if (stockNum==0){
            System.out.println("活动结束");
            return false;
        }else {
            //下单(模拟不同用户openid不同)
            orders.put(UUID.randomUUID().toString(),productId);
            //减库存
            stockNum=stockNum-1;
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
            redisLockUtils.unlock(productId,String.valueOf(time));
            return true;
        }
    }

    @Override
    public Integer queryStock(String productID) {
        return stock.get(productID);
    }
}
