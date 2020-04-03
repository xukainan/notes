package top.uaian.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * description: 测试拦截器 <br>
 * date: 2020/3/26 14:04 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test")
//@CrossOrigin
//@ExceptionHandler(MyException.class)
public class TestRequestLogController {

    @RequestMapping("/request_log")
    public String TestRequestLog(@RequestParam String name){
        return "hello " + name;
    }
}
