package com.qdu.diaisheng.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/stsIndex")
public class IndexAdminController {

   /* @RequestMapping(value = "/index")
    public String index(){
        return "admin/index";
    }*/
    @RequestMapping(value = "/qdsts")
    public String index(){
        return "admin/qdstsIndex";
    }

}
