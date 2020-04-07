package top.uaian.tool.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * description:  Redis分布式锁简单实现<br>
 * date: 2020/4/3 11:21 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Component
public class RedisLockUtils {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final long expireTime = 6000;

    public boolean lock(String key){
        long time = new Date().getTime();
        String value = String.valueOf(time + expireTime);
        if(stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //处理“锁已经过期，但是没有运行解锁操作”
        //假设获取的值是a
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //当key存在并且已经过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //处理多个线程同时进入的情况
            //A线程get旧值a， set新值b
            //B线程get旧值b， set新值b
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            //A线程oldvalue = a， currentValue =a 进入方法
            //B线程oldvalue = b， currentValue =a 不进入方法
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public boolean unlock(String key, String value){
        Long l = Long.parseLong(value) + expireTime;
        Boolean isDelete = false;
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue) && currentValue.equals(l.toString())) {
            isDelete = stringRedisTemplate.opsForValue().getOperations().delete(key);
        }
        return isDelete;
    }
}
