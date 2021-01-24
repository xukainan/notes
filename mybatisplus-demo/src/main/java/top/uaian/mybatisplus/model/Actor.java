package top.uaian.mybatisplus.model;

import lombok.Data;

import java.util.Date;

@Data
public class Actor {
    private int actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;
}
