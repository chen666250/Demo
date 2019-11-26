package com.example.demo.model;

import lombok.Data;

import java.security.PrivilegedExceptionAction;
@Data
public class User {
    private  Integer id;
    private  String  name;
    private  String account_id;
    private  String  token;
    private  Long  create_time;
    private  Long  modify_time;
    private  String avatar_url;


}
