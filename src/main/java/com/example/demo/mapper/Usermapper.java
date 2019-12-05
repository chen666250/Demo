package com.example.demo.mapper;

import com.example.demo.dto.GithubUserGTO;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface Usermapper {
    @Insert("insert into USERH (name,account_id,token,create_time,modify_time,avatar_url) values(#{name},#{account_id},#{token},#{create_time},#{modify_time},#{avatar_url})")
    void insert(User user);// mybatis 将USER传入数据库

    @Select("select * from USERH where token= #{token}")
    User findByToken(@Param("token") String token);

    @Select(("select * from USERH where id =#{post_id}"))
    User findById(@Param("post_id") Integer post_id );
    @Select(("select * from USERH where account_id =#{account_id}"))
    User findByAccountID(@Param("account_id") String account_id );

    @Update("update userh set name=#{name},token=#{token},modify_time=#{modify_time},avatar_url=#{avatar_url} where id=#{id}")
    void update(User dbuser);
}
