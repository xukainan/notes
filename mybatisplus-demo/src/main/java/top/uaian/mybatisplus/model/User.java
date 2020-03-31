package top.uaian.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:  测试用户类<br>
 * date: 2020/3/31 16:27 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Data
@TableName("User")
public class User {

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private int id;

    @TableField("name")
    private String name;

    @TableField("age")
    private int age;
}
