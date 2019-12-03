package com.example.demo.controller;

import com.example.demo.dto.TopicDto;
import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class TopicController {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private Usermapper usermapper;

    @RequestMapping("/thistopic/{topicid}")
    public String Topic(@PathVariable(name = "topicid")Integer topicid, Model model){
        TopicDto topicDto = topicMapper.findTopicbyID(topicid);
        User user= usermapper.findById(topicDto.getPost_id());
        topicDto.setCreatuser(user);
        model.addAttribute("TopicDto2",topicDto);
        System.out.println("this is tod2 "+ topicDto);


        return "topic";
    }
}
