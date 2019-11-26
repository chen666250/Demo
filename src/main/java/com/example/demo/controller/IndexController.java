package com.example.demo.controller;

import com.example.demo.dto.TopicDto;
import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.service.TopicService;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private  Usermapper usermapper;
    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest ,Model model){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = usermapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                        httpServletRequest.getSession().setAttribute("icon",user.getAvatar_url());
                        System.out.println("this is my avatat " + user.getAvatar_url());
                        break;
                    }else{
                        httpServletRequest.getSession().setAttribute("icon",null);
                    }

                }
            }

        List<TopicDto> topicsdtos = topicService.showall();
        model.addAttribute("alltopics",topicsdtos);
        System.out.println("indexcontroller "+topicsdtos.toString());


        return "index";
    }
}
