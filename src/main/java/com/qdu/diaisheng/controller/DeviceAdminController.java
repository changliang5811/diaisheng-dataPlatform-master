package com.qdu.diaisheng.controller;

import com.qdu.diaisheng.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/device")
public class DeviceAdminController {
    @RequestMapping(value = "/location",method = RequestMethod.GET)
    private String dataExport(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("loginUser");
        if(user==null){
            return "redirect:/admin/login";
        }
        return "admin/getLocation";
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    private String deviceList(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("loginUser");
        if(user==null){
            return "redirect:/admin/login";
        }
        return "admin/device";
    }
}
