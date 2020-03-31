package top.uaian.mybatisplus.dbconfig;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * description:  Mybatis-Plus配置类<br>
 * date: 2020/3/31 16:18 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Configuration
@EnableTransactionManagement
@MapperScan("top.uaian.mybatisplus.dao")
public class MybatisPlusConfig {

}
