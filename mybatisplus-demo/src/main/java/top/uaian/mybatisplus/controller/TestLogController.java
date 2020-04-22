package top.uaian.mybatisplus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:  测试logback<br>
 * date: 2020/4/22 16:39 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test")
public class TestLogController {

    Logger logger = LoggerFactory.getLogger(TestLogController.class);

    @RequestMapping("/log")
    public void log(){
        logger.info("test log---------");
    }
}
