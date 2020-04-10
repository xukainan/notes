package top.uaian.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * description: 消息发布者 <br>
 * date: 2020/4/10 10:04 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test")
public class TestRedisMessageSender {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/send/{msg}")
    public void sendMsg(@PathVariable String msg) {
        stringRedisTemplate.convertAndSend("chat", "hello_"+ msg + new Date());
    }
}
