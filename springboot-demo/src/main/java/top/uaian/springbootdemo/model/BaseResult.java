package top.uaian.springbootdemo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:  基础返回类<br>
 * date: 2020/4/3 10:50 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
@Data
public class BaseResult implements Serializable {
    private boolean success ;
    private int code ;
    private String message;
    private Object data ;
}
