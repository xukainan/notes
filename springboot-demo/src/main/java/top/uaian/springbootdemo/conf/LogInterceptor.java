package top.uaian.springbootdemo.conf;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:  日志拦截器<br>
 * date: 2020/3/26 9:26 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求的路径为: "+ request.getRequestURI() + ", 请求的参数为：" + JSON.toJSONString(request.getParameterMap()));
        return true;
    }
}
