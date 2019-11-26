package com.example.demo.controller;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private Usermapper usermapper;

    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    @PostMapping("/publish")
    public  String commitTopic(
            @RequestParam("title")String title,
            @RequestParam("topiccontext")String topiccontext,
            @RequestParam("tags")String tags,
            HttpServletRequest httpServletRequest,
            Model model
    ){

        model.addAttribute("title",title);
        model.addAttribute("topiccontext",topiccontext);
        model.addAttribute("tags",tags);


        //理论上输入校验要放到前端，但老子嫌麻烦。而且没怎么用过js。
        if(title==null ||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(topiccontext==null ||topiccontext==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = usermapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }

        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Topic mytopic = new Topic();
        mytopic.setTitle(title);
        mytopic.setTopiccontext(topiccontext);
        mytopic.setTags(tags);
        mytopic.setPost_id(user.getId());
        mytopic.setCreate_time(System.currentTimeMillis());
        mytopic.setModify_time(mytopic.getCreate_time());
        topicMapper.create(mytopic);
        return "redirect:/";
    }


}
