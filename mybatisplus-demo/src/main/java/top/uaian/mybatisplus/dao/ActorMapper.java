package top.uaian.mybatisplus.dao;

import org.apache.ibatis.annotations.Param;
import top.uaian.mybatisplus.model.Actor;

public interface ActorMapper {

    Actor getActorByID(@Param("id") int id);
}
