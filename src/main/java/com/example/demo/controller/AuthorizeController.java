package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private Githubprovider githubprovider;
    @GetMapping ("/callback")
    public String callback(@RequestParam(name="code")String code ,
                           @RequestParam(name="state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("4c2b0e2cf07a428b2ead");
        accessTokenDto.setClient_secret("f5509ff75ac06909541bc8df85409b53c97ce4e1");
        githubprovider.getaccesstoken(accessTokenDto);

        return "index";
    }

}
