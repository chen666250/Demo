package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUserGTO;
import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import com.example.demo.provider.Githubprovider;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private Githubprovider githubprovider;
    @Value("${github.client.id}")
    private  String githubid;

    @Value("${github.client.secret}")
    private  String githubsecret;
    @Value("${github.redirect.uri}")
    private  String githuburl;

    @Autowired
    private Usermapper usermapper;


    @GetMapping ("/callback")
    public String callback(@RequestParam(name="code")String code ,
                           @RequestParam(name="state") String state,
                           HttpServletRequest httpServletRequest){


        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(githuburl);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(githubid);
        accessTokenDto.setClient_secret(githubsecret);
        String token= githubprovider.getaccesstoken(accessTokenDto);
        GithubUserGTO githubUserGTO =githubprovider.getUser(token);
        System.out.println("user to str "+githubUserGTO.toString());
        System.out.println("this is user "+githubUserGTO.getName());
        if(githubUserGTO!=null){
            User modeluser = new User();
            modeluser.setToken(UUID.randomUUID().toString());
            modeluser.setName(githubUserGTO.getName());
            modeluser.setAccountid(String.valueOf(githubUserGTO.getId()));
            modeluser.setCreate_time(System.currentTimeMillis());
            modeluser.setModify_time(modeluser.getCreate_time());
            usermapper.insert(modeluser);
            httpServletRequest.getSession().setAttribute("user",githubUserGTO);
            return "redirect:/";//redirect 返回的是地址
        }else {
            return "redirect:/";
        }


    }

}
