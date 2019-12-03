package com.example.demo.mapper;

import com.example.demo.dto.TopicDto;
import com.example.demo.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicMapper {

    @Insert("insert into topic (idtopic,title,topiccontext,create_time,modify_time,post_id,tags) values(#{idtopic},#{title},#{topiccontext}," +
            "#{create_time},#{modify_time},#{post_id},#{tags})")
    void create(Topic topic);

    @Select("select * from topic order by idtopic ${Des} limit #{offset},#{size}")
    List<Topic> showall(@Param(value = "offset")Integer offset,@Param(value = "size") Integer size,@Param( "Des") String Des);

    @Select( "select count(1) from topic ")
    Integer countTopics();

    @Select("select * from topic where idtopic=#{topicid}")
    TopicDto findTopicbyID(Integer topicid);
}
