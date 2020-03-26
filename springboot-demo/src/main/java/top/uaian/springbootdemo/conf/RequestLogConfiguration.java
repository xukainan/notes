package top.uaian.springbootdemo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description:  请求日志配置类<br>
 * date: 2020/3/26 9:23 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Configuration
public class RequestLogConfiguration {

    @Autowired
    private LogInterceptor logInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(logInterceptor).addPathPatterns("/**");
            }
        };
    }

}
