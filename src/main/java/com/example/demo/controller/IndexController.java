package com.example.demo.controller;

import com.example.demo.dto.PageDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(name ="currentPage",defaultValue = "1") Integer currentPage,
                        @RequestParam(name ="size",defaultValue = "8") Integer size,
                         @RequestParam(name="isDes",defaultValue = "0" ) Integer isDes){
        PageDto pageDto =topicService.showall(currentPage,size,isDes);
        System.out.println("isDes" +isDes );
            model.addAttribute("pageDto",pageDto);
//        System.out.println("this is pageDto "+pageDto);


        return "index";
    }
}
