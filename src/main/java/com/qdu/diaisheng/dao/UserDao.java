package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserDao {
    int findByUserNameAndPassword(@Param("userName")String userName,
                                    @Param("password")String password);//通过用户名以及密码登录系统，返回查询出的个数
    boolean addUser(User user);

    User findByUserName(String userName);

    int varifyNamePwdAdmin(Map<String,Object> map);//验证管理员登录，返回用户个数

}
