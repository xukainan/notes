package top.uaian.mybatisplus.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * description:  当前数据源<br>
 * date: 2020/3/31 15:34 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
public class DbContextHolder {
    private static Logger log = LoggerFactory.getLogger(DbContextHolder.class);

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 默认数据源
     */
    @Value("${dbconfig.defaultDataName}")
    private static String defaultDbSource = "";

    /**
     * 设置数据源
     *
     * @param dbType
     */
    static String dbType = "";

    public static String getDbType() {
        if (contextHolder.get() == null) return defaultDbSource;
        else return contextHolder.get();
    }

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
        log.debug("clean DbContextHolder database key");
    }
}
