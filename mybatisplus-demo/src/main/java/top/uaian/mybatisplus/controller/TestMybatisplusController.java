package top.uaian.mybatisplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.uaian.mybatisplus.model.User;
import top.uaian.mybatisplus.service.UserService;

/**
 * description:  测试mybatis-plus接口<br>
 * date: 2020/3/31 13:39 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/test")
public class TestMybatisplusController {

    @Autowired
    UserService userService;

    @RequestMapping("/adduser")
    public String addUser(@RequestParam("name") String name, @RequestParam("age") int age){
        boolean add = userService.add(name, age);
        if(add) {
            return "增加成功";
        }
        return "增加失败";
    }

    @RequestMapping("getUSer")
    public User getUser(@RequestParam("id")int id) {
        return userService.get(id);
    }
}
