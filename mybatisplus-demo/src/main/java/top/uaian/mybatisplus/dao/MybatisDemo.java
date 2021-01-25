package top.uaian.mybatisplus.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.ResourceUtils;
import top.uaian.mybatisplus.model.Actor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        String resource = "classpath:mybatis-config.xml";
        File file = ResourceUtils.getFile(resource);
        InputStream resourceAsStream = new FileInputStream(file);
//        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        Actor actor = sqlSession.selectOne("top.uaian.mybatisplus.dao.ActorMapper.getActorByID", 11);
        System.out.println(actor);
    }
}
