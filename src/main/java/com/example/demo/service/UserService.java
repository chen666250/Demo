package com.example.demo.service;

import com.example.demo.mapper.Usermapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private Usermapper usermapper;

    public void insertorUpdate(User modeluser) {
        User dbuser=usermapper.findByAccountID(modeluser.getAccount_id());

        if(dbuser!=null){

            modeluser.setModify_time(modeluser.getCreate_time());
            dbuser.setAvatar_url(modeluser.getAvatar_url());
            dbuser.setName(modeluser.getName());
            dbuser.setToken(modeluser.getToken());
            usermapper.update(dbuser);
            //do update

        }else {
            //do insert
            modeluser.setCreate_time(System.currentTimeMillis());
            modeluser.setModify_time(modeluser.getCreate_time());
            usermapper.insert(modeluser);
        }
    }
}
