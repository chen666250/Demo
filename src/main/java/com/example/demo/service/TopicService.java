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

    public   PageDto showall(Integer currentPage,Integer size,Integer isDes) {

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
        String des=null;
        if(isDes!=1){
            des="asc";
        }else {
            des="desc";
        }
        System.out.println("topservice des is " + des);
        List<Topic> topics=topicMapper.showall(offset,size,des);
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

    public PageDto showmyall(String account_id, Integer currentPage, Integer size) {
        PageDto allpage=  this.showall(currentPage,size,0);
        List<TopicDto> tiopics= allpage.getTiopics();
        List<TopicDto> usertiopics = new ArrayList<TopicDto>();
        for (TopicDto tiopic : tiopics) {
            if(tiopic.getCreatuser().getAccount_id().equals(account_id)){
                usertiopics.add(tiopic);
            }
        }
        tiopics=null;
        allpage.setTiopics(usertiopics);
        return allpage;
    }

    public PageDto showbySearch(String searchcontent) {
        return null;
    }
    public  TopicDto findById(Integer topicid){
       return topicMapper.findTopicbyID(topicid);
    }
}
