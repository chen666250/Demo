package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUserGTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Githubprovider {
public String getaccesstoken(AccessTokenDto accessTokenDto) {
     MediaType JSON2 = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON2, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();
            String[] split = string.split("&");
            String tokenstr= split[0];
            String Token = tokenstr.split("=")[1];
            System.out.println(Token);
            return Token ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
}
public GithubUserGTO getUser(String accessToken){
    OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
    try {
        Response response = client.newCall(request).execute();
        String string =response.body().string();
        GithubUserGTO githubUserGTO = JSON.parseObject(string, GithubUserGTO.class);
        return  githubUserGTO;
    } catch (IOException e) {
        e.printStackTrace();
    }return  null;


}

}




