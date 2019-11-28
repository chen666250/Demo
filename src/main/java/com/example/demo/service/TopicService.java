package com.example.demo.service;

import com.example.demo.dto.PageDto;
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

    public   PageDto showall(Integer currentPage,Integer size) {
        PageDto pageDto = new PageDto();
        Integer totalCount=topicMapper.countTopics();
        pageDto.setPage(totalCount,currentPage,size);
        if(currentPage<1){
            currentPage=1;
        }
        if(currentPage>pageDto.getTotalPages()){
            currentPage=pageDto.getTotalPages();
        }

        Integer offset =size*(currentPage-1);

        List<Topic> topics=topicMapper.showall(offset,size);
     List<TopicDto> topicsDtos =new ArrayList<>();
        for (Topic topic : topics) {
           User user= usermapper.findById(topic.getPost_id());
           TopicDto topicDto = new TopicDto();
            BeanUtils.copyProperties(topic,topicDto);
            topicDto.setCreatuser(user);
            topicsDtos.add(topicDto);

        }
        pageDto.setTiopics(topicsDtos);
        return pageDto;
    }
}
