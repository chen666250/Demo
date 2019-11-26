package com.example.demo.mapper;

import com.example.demo.dto.GithubUserGTO;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Usermapper {
    @Insert("insert into USERH (name,account_id,token,create_time,modify_time,avatar_url) values(#{name},#{account_id},#{token},#{create_time},#{modify_time},#{avatar_url})")
    void insert(User user);// mybatis 将USER传入数据库

    @Select("select * from USERH where token= #{token}")
    User findByToken(@Param("token") String token);

    @Select(("select * from USERH where id =#{post_id}"))
    User findById(@Param("post_id") Integer post_id );
}
