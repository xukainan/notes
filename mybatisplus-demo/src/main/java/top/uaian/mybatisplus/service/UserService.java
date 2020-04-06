package top.uaian.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.uaian.mybatisplus.model.User;

public interface UserService extends IService<User> {
    boolean add(String name, int age);
    User get(int id);
}
