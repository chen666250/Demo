package com.example.demo.controller;

import com.example.demo.dto.PageDto;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class profileController {
    @Autowired
    private Usermapper usermapper;

    @Autowired
    private TopicService topicService;
    @GetMapping("/personal/{action}")
    public  String profile(@PathVariable(name = "action") String action,
                           Model model,
                           HttpServletRequest httpServletRequest, @RequestParam(name ="currentPage",defaultValue = "1") Integer currentPage,
                           @RequestParam(name ="size",defaultValue = "8") Integer size){



        User user = null;
         user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }








        if("topics".equals(action)){
            model.addAttribute("section","topics");
            model.addAttribute("sectionTitle","我的话题");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionTitle","我的回复");
        }
        System.out.println("my id is"+user.getId());
        PageDto allpage = topicService.showmyall(user.getAccount_id(),currentPage,size);
        model.addAttribute("pageDto",allpage);
        return "profile";
    }
}
