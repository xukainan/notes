package top.uaian.tool.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description:  Redis实现生产者消费者消息队列<br>
 * date: 2020/4/7 14:12 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Component
public class RedisMQUtils {

    @Autowired
    RedisTemplate redisTemplate;

    public Long produce(String key, String msg) {
        Map<String,String> map = new HashMap<>();
        map.put("orderId", msg);
        Long result = redisTemplate.opsForList().leftPush(key, map);
        Properties info = redisTemplate.getRequiredConnectionFactory().getConnection().info("server");
        System.out.println(redisTemplate.opsForList().rightPop(key, 40, TimeUnit.SECONDS));
        return result;
    }

    public void consumer(){
        String key = "myorder";
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(()-> {
            while (true) {
                Object o = redisTemplate.opsForList().rightPop(key, 40, TimeUnit.SECONDS);
                String msg = JSON.toJSONString(o);
                if(msg == "null") {
                    continue;
                }
                System.out.println("已经消费消息:" + msg);

            }

        });

    }
}
