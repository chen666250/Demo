package com.example.demo.mapper;

import com.example.demo.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper {

    @Insert("insert into topic (idtopic,title,topiccontext,create_time,modify_time,post_id,tags) values(#{idtopic},#{title},#{topiccontext}," +
            "#{create_time},#{modify_time},#{post_id},#{tags})")
    void create(Topic topic);
}
