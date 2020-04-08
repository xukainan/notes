package top.uaian.springbootdemo.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description: 测试限流Guava <br>
 * date: 2020/4/7 17:09 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RequestMapping("/test/guava")
@RestController
public class TestGuavaController {
    /**
     * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     * 当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     * 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     */
//    static final RateLimiter rateLimiter = RateLimiter.create(1);
    /**
     * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     * 还包含一个热身期(warmup period),热身期内，RateLimiter会平滑的将其释放令牌的速率加大，直到起达到最大速率
     * 同样，如果RateLimiter在热身期没有足够的请求(unused),则起速率会逐渐降低到冷却状态
    **/
    static final RateLimiter rateLimiter = RateLimiter.create(10,5, TimeUnit.SECONDS);

    @RequestMapping("addorder")
    public void addOrder() throws InterruptedException {
        //尝试获取令牌，获取失败就返回
        //加参数permits 令牌数
        //加参数timeout 超时时间
        if(rateLimiter.tryAcquire()) {
            Thread.sleep(1000);
            System.out.println("下单成功");
        }else {
            System.out.println("下单失败");
        }
    }

    @RequestMapping("addorder2")
    public void addOrder2() throws InterruptedException {
        //尝试获取令牌，获取失败就返回
        //加参数permits 令牌数
        rateLimiter.acquire();
        Thread.sleep(1000);
        System.out.println(new Date() + "下单成功");

    }


}
