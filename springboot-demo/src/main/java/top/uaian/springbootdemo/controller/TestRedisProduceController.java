package top.uaian.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.uaian.tool.utils.RedisMQUtils;

/**
 * description:  测试Redis生产者<br>
 * date: 2020/4/7 15:14 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test/produce")
public class TestRedisProduceController {

    @Autowired
    RedisMQUtils redisMQUtils;

    @RequestMapping("/addOrder/{orderId}")
    public String addOrder(@PathVariable String orderId){
        String queueName = "myorder";
        Long produce = redisMQUtils.produce(queueName, orderId);
        if(produce > 0) {
            return "下单成功";
        }
        return "下单失败";
    }
}
