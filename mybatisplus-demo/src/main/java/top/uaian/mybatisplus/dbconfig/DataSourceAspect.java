package top.uaian.mybatisplus.dbconfig;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.uaian.mybatisplus.model.DbContextHolder;
import top.uaian.mybatisplus.model.DbSourceConstant;

/**
 * description:  <br>
 * date: 2020/3/31 16:49 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("execution(* top.uaian.mybatisplus.dao.sel*(..))" +
                "|| execution(* top.uaian.mybatisplus.dao.count*(..))" +
            "|| execution(* top.uaian.mybatisplus.dao.find*(..))" +
            "|| execution(* top.uaian.mybatisplus.dao.get*(..))")
    private void db_read(){}

    @Before("db_read()")
    public void db_setReadDataSource(){
        if (logger.isDebugEnabled()) {
            logger.info("切换到db读库");
        }
        DbContextHolder.setDbType(DbSourceConstant.db_READ);
    }

    @After("db_read()")
    public void db_releaseReadDataSource(){
        if (logger.isDebugEnabled()) {
            logger.info("释放db读库");
        }
        DbContextHolder.clearDbType();
    }

    @Pointcut("execution(* top.uaian.mybatisplus.dao.insert*(..))"+
            "|| execution(* top.uaian.mybatisplus.dao.update*(..))" +
            "|| execution(* top.uaian.mybatisplus.dao.wget*(..))" +
            "|| execution(* top.uaian.mybatisplus.dao.save*(..))" +
            "|| execution(* top.uaian.mybatisplus.dao.del*(..))"
    )
    private void db_write(){};

    @Before("db_write()")
    public void db_setWriteDataSource(){
        if (logger.isDebugEnabled()) {
            logger.info("切换到db写库");
        }
        DbContextHolder.setDbType(DbSourceConstant.db_WRITE);
    }

    @After("db_write()")
    public void db_releaseWriteDataSource(){
        if (logger.isDebugEnabled()) {
            logger.info("释放db写库");
        }
        DbContextHolder.clearDbType();
    }
}
