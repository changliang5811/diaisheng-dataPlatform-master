package com.qdu.diaisheng.controller;

import com.qdu.diaisheng.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/model")
@Controller
public class DataModelAdminController {
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String ModelList(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("loginUser");
        if(user==null){
            return "redirect:/admin/login";
        }
        return "admin/dataModel";
    }
}
