package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.invoke.MethodHandles;

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

    @GetMapping ("/callback")
    public String callback(@RequestParam(name="code")String code ,
                           @RequestParam(name="state") String state){


        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(githuburl);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(githubid);
        accessTokenDto.setClient_secret(githubsecret);
        githubprovider.getaccesstoken(accessTokenDto);

        return "index";
    }

}
