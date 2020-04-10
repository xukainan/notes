package top.uaian.tool.utils;

import org.springframework.stereotype.Component;

/**
 * description:  接收消息<br>
 * date: 2020/4/10 9:52 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Component
public class RedisReceiver {

    public void receiveMessage(String msg){
        System.out.println("接收消息：" + msg);
    }
}
