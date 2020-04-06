package top.uaian.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.uaian.mybatisplus.dao.UserMapper;
import top.uaian.mybatisplus.model.User;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean add(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        int insert = userMapper.insert(user);
        if(insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User get(int id) {
        return userMapper.selectById(id);
    }
}
