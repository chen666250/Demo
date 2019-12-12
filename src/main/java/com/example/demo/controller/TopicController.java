package com.example.demo.controller;

import com.example.demo.Error.IError;
import com.example.demo.Error.MyError;
import com.example.demo.Error.TheError;
import com.example.demo.dto.TopicDto;
import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TopicController {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private Usermapper usermapper;
    @Autowired
    private  TopicService topicService;
    @RequestMapping("/thistopic/{topicid}")
    public String Topic(@PathVariable(name = "topicid")Integer topicid, Model model){

        TopicDto topicDto = topicMapper.findTopicbyID(topicid);
        if(topicDto==null){
            throw new MyError(TheError.QUESTION_NOT_FOUND);
        }
        User user= usermapper.findById(topicDto.getPost_id());
        topicDto.setCreatuser(user);
        model.addAttribute("TopicDto2",topicDto);
//        System.out.println("this is tod2 "+ topicDto);


        return "topic";
    }
    @RequestMapping("/modifyTopic/{topicid}")
    public String modifyTopic(Model model, @PathVariable Integer topicid){
        TopicDto topicDto = topicService.findById(topicid);
        User user= usermapper.findById(topicDto.getPost_id());
        topicDto.setCreatuser(user);
        model.addAttribute("TopicDtoInModify",topicDto);

        return "ModifyTopic";
    }
    @PostMapping("/modifyTopic")
        public String submitModify(@RequestParam("topicid") Integer topicid,
                                   @RequestParam("topiccontext")String topiccontext,
                                   @RequestParam("tags")String tags){
        TopicDto topicDto = topicMapper.findTopicbyID(topicid);

        User user= usermapper.findById(topicDto.getPost_id());
        topicDto.setCreatuser(user);
//        System.out.println("got id in modifying"+ topicid);
        topicDto.setTopiccontext(topiccontext);
        topicDto.setTags(tags);
        topicDto.setModify_time(System.currentTimeMillis());
        int isOk= topicMapper.update(topicDto);
       if(isOk==1){
           System.out.println("successful modify");
       }else{
           throw  new MyError("更新失败");
       }
        return "redirect:/";
    }

}
