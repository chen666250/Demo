package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class TopicDto {
    private  Integer idtopic;
    private  String title;
    private  String topiccontext;
    private  Long   create_time;
    private  Long   modify_time;
    private  Integer read_count;
    private  Integer comment_count;
    private  Integer like_count;
    private  String tags;
    private  Integer post_id;
    private  User   creatuser;
}
