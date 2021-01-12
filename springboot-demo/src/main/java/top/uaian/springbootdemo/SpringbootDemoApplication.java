package top.uaian.springbootdemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import top.uaian.springbootdemo.conf.apollo.ApolloConfig;

@SpringBootApplication
@ComponentScan(basePackages = {"top.uaian.springbootdemo","top.uaian.tool"})
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        ApolloConfig.Initialize(args);
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
