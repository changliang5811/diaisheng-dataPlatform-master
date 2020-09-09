package com.qdu.diaisheng.service;

import com.qdu.diaisheng.entity.User;

import java.util.Map;

public interface UserService {
    int login(String user_name,String password);
    boolean register(User user);

    User findByUserName(String name);

    int varifyNamePwdAdmin(Map<String,Object> map);//验证管理员登录，返回用户个数
}
