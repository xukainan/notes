package top.uaian.jdk;

import top.uaian.mybatisplus.model.User;

import java.util.Optional;

public class OptionTest {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(18);
        if(Optional.ofNullable(user).map(u -> u.getName()).isPresent()){
            System.out.println("1111");
        }
        String str = Optional.ofNullable(user).ofNullable(user.getName()).orElse(user.getAddress());

    }
}
