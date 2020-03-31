package top.uaian.mybatisplus.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * description:  SQL dirver秘钥对<br>
 * date: 2020/3/31 10:58 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Data
public class SQLDriverItem {

    @JSONField(name = "DBname")
    private String dbName;

    @JSONField(name = "DBtype")
    private String dbType;

    @JSONField(name = "DBaddr")
    private String dbAddr;

    @JSONField(name = "DBport")
    private String dbPort;

    @JSONField(name = "DBusertype")
    private String dbUserType;

    @JSONField(name = "DBusername")
    private String dbUserName;

    @JSONField(name = "DBpw")
    private String dbPW;

    /**
     * 获取驱动名称和jdbc url
     *
     * @return pair：第一个为driver名称  第二个为jdbc url
     */
    public KeyPair getDriverAndUrl() throws Exception {
        String lowerDbType = dbType.toLowerCase();

        switch (lowerDbType) {
            case "mysql":
                return new KeyPair("com.mysql.jdbc.Driver",
                        "jdbc:mysql://" + dbAddr + ":" + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
            case "oracle":
                return new KeyPair("oracle.jdbc.driver.OracleDriver",
                        "jdbc:oracle:thin:@" + dbAddr + ":" + dbPort + ":" + dbName);
            case "sqlserver":
                return new KeyPair("com.microsoft.sqlserver.jdbc.SQLServerDriver",
                        "jdbc:sqlserver://" + dbAddr + ":" + dbPort + ";DatabaseName=" + dbName);
            case "vertica":// jdbc:vertica://124.251.48.127:5433/vertica_fwyun
                return new KeyPair("com.vertica.jdbc.Driver",
                        "jdbc:vertica://" + dbAddr + ":" + dbPort + "/" + dbName);
            default:
                throw new Exception("not support jdbc driver");
        }
    }

}
