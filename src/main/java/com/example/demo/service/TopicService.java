package com.example.demo.service;

import com.example.demo.dto.TopicDto;
import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private Usermapper usermapper;

    public  List<TopicDto> showall() {
     List<Topic> topics=topicMapper.showall();
     List<TopicDto> topicsDtos =new ArrayList<>();
        for (Topic topic : topics) {
           User user= usermapper.findById(topic.getPost_id());
           TopicDto topicDto = new TopicDto();
            BeanUtils.copyProperties(topic,topicDto);
            topicDto.setCreatuser(user);
            topicsDtos.add(topicDto);

        }

        return topicsDtos;
    }
}
