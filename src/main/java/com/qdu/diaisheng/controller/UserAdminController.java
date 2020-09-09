package com.qdu.diaisheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @Author changliang
 * @Description 登录页面跳转
 * @Date 2019/7/19 18:30
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class UserAdminController {
   /*
    * @Author changliang
    * @Description 跳转普通用户登录界面
    * @Date 2019/7/19 18:32
    * @Param []
    * @return admin/login.html
    **/
    @RequestMapping(value = "/login")
    private String login(){
        return "admin/login";
    }
   /*
    * @Author changliang
    * @Description 跳转到管理员登录界面
    * @Date 2019/7/19 18:33
    * @Param []
    * @return admin/loginAdmin.html
    **/
    @RequestMapping(value = "/loginAdmin")
    private String loginAdmin(){
        return "admin/loginAdmin";
    }
    /*注册协议*/
    @RequestMapping(value = "/agreement")
    private String agreement(){
        return "admin/agreement";
    }
}
