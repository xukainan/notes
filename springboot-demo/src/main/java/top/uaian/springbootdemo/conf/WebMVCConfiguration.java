package top.uaian.springbootdemo.conf;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.uaian.springbootdemo.model.BaseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description:  WebMVC的个性化配置<br>
 * date: 2020/4/3 10:11 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    /**
     * 是否包含.*来映射请求
     * 假设RequestMapping注解中指定的路径是/test
     * 如果设置成True，那么对于/test.do,/test.a等任何包含.的请求都会映射到/test上去；
     * 如果设置成False，那么对于这种请求不会进行映射。
     *
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(true);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
            resolvers.add(new HandlerExceptionResolver() {
                @Override
                public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
                    BaseResult baseResult = new BaseResult();
                    if(e instanceof MissingServletRequestParameterException) {
                        baseResult.setCode(-1);
                        baseResult.setMessage("系统参数异常，请联系管理员");
                    }

                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
                    try {
                        httpServletResponse.getWriter().write(JSON.toJSONString(baseResult));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return new ModelAndView();
                }
            });
    }
}
