package top.uaian.mybatisplus.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import top.uaian.mybatisplus.model.DbContextHolder;

/**
 * description:  动态数据源<br>
 * date: 2020/3/31 15:24 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
