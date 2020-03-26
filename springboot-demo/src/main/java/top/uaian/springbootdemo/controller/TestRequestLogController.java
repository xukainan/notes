package top.uaian.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 测试拦截器 <br>
 * date: 2020/3/26 14:04 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test")
public class TestRequestLogController {

    @RequestMapping("/request_log")
    public String TestRequestLog(@RequestParam String name){
        return "hello " + name;
    }
}
