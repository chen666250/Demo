package com.example.demo.controller;

import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private  Usermapper usermapper;
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = usermapper.findByToken(token);
                if(user!=null){
                    httpServletRequest.getSession().setAttribute("user",user);
                    break;
                }

            }
        }



        return "index";
    }
}
