package top.uaian.springbootdemo.conf.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.Objects;

/**
 * description:  apollo配置类<br>
 * date: 2020/4/22 14:35 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
public class ApolloConfig {

    public static void Initialize(String[] args){
        //要在程序之前启动，
        if(args.length > 0) {
            for (String arg : args) {
                if(arg.toLowerCase().contains("spring.profiles.active")) {
                    String[] split = arg.split("=");
                    if(split.length == 2) {
                        String curEnv = split[1];
                        if(Objects.equals(curEnv, "dev")) {
                            System.setProperty("env", "DEV");
                            //apollo.meta的地址
                            System.setProperty("apollo.meta", "192.168.47.128:8080");
                            //在应用启动阶段，向Spring容器注入被托管的application.properties文件的配置信息。
                            System.setProperty("apollo.bootstrap.enabled", "true");
                            //apollo的命名空间
                            System.setProperty("apollo.bootstrap.namespaces", "application");
                        }else if(Objects.equals(curEnv, "test")){

                        }
                    }
                }
            }
        }
        Config config = ConfigService.getAppConfig();

    }

}
