//package top.uaian.springbootdemo.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * description:  全局跨域配置<br>
// * date: 2020/3/30 9:04 <br>
// * @author: xukainan <br>
// * version: 1.0 <br>
// */
//@Configuration
//public class CorsConfig {
//    private CorsConfiguration corsConfiguration(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod(HttpMethod.GET);
//        corsConfiguration.addAllowedMethod(HttpMethod.POST);
//        corsConfiguration.addAllowedOrigin("*");
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration());
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }
//}
