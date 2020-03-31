package top.uaian.mybatisplus.dbconfig;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import top.uaian.mybatisplus.model.KeyPair;
import top.uaian.mybatisplus.model.SQLDriverItem;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * description:  数据源配置<br>
 * date: 2020/3/30 15:33 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;//spring 的运行环境


    /**
     * 数据库配置文件路径
     */
    @Value("${dbconfig.file.location}")
    private String location;

    /**
     * 数据库配置文件的默认数据源名称
     */
    @Value("${dbconfig.defaultDataName}")
    private String defaultDataName;

    @PostConstruct //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
    private void init(){
        //druid 的配置，详见https://www.bookstack.cn/read/Druid/8bf7e2f602686bb8.md
        String filters = this.environment.getProperty("$DB_DRUID.filters");
        if(!StringUtils.isEmpty(filters)){
            //通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
            System.setProperty("druid.filters", filters);
        }
        //不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        System.setProperty("druid.testWhileIdle", "true");
        //用来检测连接是否有效的sql
        System.setProperty("druid.validationQuery", "select 1");
        //取消testOnBorrow，该值标识每次getConnection时都进行检测，比较影响性能
        System.setProperty("druid.testOnBorrow", "false");
    }

    private DataSource buildDataSource(SQLDriverItem sqlDriverItem) throws Exception {
        //druid 的配置，详见https://www.bookstack.cn/read/Druid/8bf7e2f602686bb8.md
        if(!Optional.ofNullable(sqlDriverItem).isPresent()) {
            try {
                throw new Exception("buildDataSource 参数sqlDriverItem 为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        KeyPair driverAndUrl = sqlDriverItem.getDriverAndUrl();
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(driverAndUrl.getValue());
        datasource.setUsername(sqlDriverItem.getDbUserName());
        datasource.setPassword(sqlDriverItem.getDbPW());
        datasource.setDriverClassName(driverAndUrl.getKey());

        datasource.setInitialSize(5);
        datasource.setMinIdle(1);
        datasource.setMaxActive(20);
        datasource.setMaxWait(10*1000L);
        datasource.setTimeBetweenEvictionRunsMillis(60 * 1000L);
        datasource.setMinEvictableIdleTimeMillis(300 * 1000L);
        datasource.setValidationQuery("select 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(false);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            datasource.setFilters("stat");
            List<Filter> filters = new ArrayList<>();
            filters.add(wallFilter());
            datasource.setProxyFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: " + e);
        }
        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        return datasource;

    }

    @Bean
    WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    WallConfig wallConfig() {
        //配置详见https://www.bookstack.cn/read/Druid/ffdd9118e6208531.md
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);//允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);//允许执行DDL
        wallConfig.setStrictSyntaxCheck(false);//Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false
        return wallConfig;
    }


    private Map<String, SQLDriverItem> getSQLDriverItemMap(String location) throws IOException {
        if(StringUtils.isEmpty(location)) {
            throw new IllegalArgumentException("location is null");
        }
        File configFile = new File(location); //加载本地文件
        if(!configFile.exists()) {
            configFile = ResourceUtils.getFile(location); //加载网络文件
            if(!configFile.exists()){
                throw new FileNotFoundException("$location 不存在");
            }
        }
        FileInputStream fileInputStream = new FileInputStream(configFile);
        StringBuffer tmpConfigStr = new StringBuffer("");
        try{
            int readCount = 0;
            byte[] bytes = new byte[512];
            while ((readCount = fileInputStream.read(bytes,0, bytes.length)) != -1) {
                tmpConfigStr.append(new String(bytes, 0 ,readCount));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileInputStream.close();
        }

        return JSONObject.parseObject(tmpConfigStr.toString(),
                new TypeReference<Map<String, SQLDriverItem>>(){}.getType());

    }

    DynamicDataSource dynamicDataSource() throws Exception {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> targetDataSource = new HashMap<>();
        Map<String, SQLDriverItem> sqlDriverItemMaps = getSQLDriverItemMap(location);
        sqlDriverItemMaps.keySet().forEach(sqlDriverItemMap_key -> {
            try {
                DataSource dataSource = buildDataSource(sqlDriverItemMaps.get(sqlDriverItemMap_key));
                targetDataSource.put(sqlDriverItemMap_key, dataSource);
                if(sqlDriverItemMap_key == defaultDataName) {
                    dynamicDataSource.setDefaultTargetDataSource(dataSource);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        dynamicDataSource.setTargetDataSources(targetDataSource);
        return dynamicDataSource;
    }
}
