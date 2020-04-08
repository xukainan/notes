package top.uaian.springbootdemo.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 测试限流Guava <br>
 * date: 2020/4/7 17:09 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RequestMapping("/test/guava")
@RestController
public class TestGuavaController {

    static final RateLimiter rateLimiter = RateLimiter.create(1);

    @RequestMapping("addorder")
    public void addOrder() throws InterruptedException {
        if(rateLimiter.tryAcquire()) {
            Thread.sleep(1000);
            System.out.println("下单成功");
        }else {
            System.out.println("下单失败");
        }
    }


}
