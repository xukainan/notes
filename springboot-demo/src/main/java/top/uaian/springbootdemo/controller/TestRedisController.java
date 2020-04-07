package top.uaian.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.uaian.springbootdemo.service.OrderService;

/**
 * description:  测试Redis缓存、分布式锁、消息队列<br>
 * date: 2020/4/7 10:34 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */

@RestController
@RequestMapping("/test")
public class TestRedisController {

    @Autowired
    OrderService orderService;

    @RequestMapping("addOrder/{productID}")
    public String addOrder(@PathVariable String productID){
        if(orderService.addOrder(productID)) {
            Integer stock = orderService.queryStock(productID);
            System.out.println("下单成功！产品还剩" + stock);
            return "下单成功！产品还剩" + stock;
        }
        System.out.println("下单失败");
        return "下单失败";
    }
}
