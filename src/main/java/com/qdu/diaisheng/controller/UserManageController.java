package com.qdu.diaisheng.controller;


import com.qdu.diaisheng.entity.Result;
import com.qdu.diaisheng.entity.User;
import com.qdu.diaisheng.service.TokenService;
import com.qdu.diaisheng.service.UserService;
import com.qdu.diaisheng.util.Md5;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/varify")
public class UserManageController {

    @Autowired
    public UserService userService;
    @Autowired
    public TokenService tokenService;

    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * @Author changliang
     * @Description 用户登录验证，传入的userName和password直接自动set到user这个pojo类当中进行处理
     * @Date 2019/7/21 16:19
     * @Param [user, request]
     * @return
     **/
    @RequestMapping(value = "/logincheck",method = RequestMethod.POST)
    @ResponseBody
    public Result loginUser(User user,HttpServletRequest request) {
       // Map<String, Object> modelMap = new HashMap<String, Object>();
        Result result = new Result();

        String passwordMd5 = Md5.md5(user.getPassword());
        HttpSession session = request.getSession();
        int i = userService.login(user.getUserName(), passwordMd5);
        try{
            if (i ==1) {
                user=userService.findByUserName(user.getUserName());
                session.setAttribute("loginUser", user);
                result.setSuccess(true);
                result.setMsg("登陆成功，欢迎您！");

            } else {
                result.setSuccess(false);
                result.setErrorMsg("用户名或密码错误！");
            }
        }catch (Exception e){
            logger.error("用户登录失败！",e);
            result.setSuccess(false);
            result.setErrorMsg("数据库异常！");
        }
        return result;
    }

    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    @ResponseBody
    public Result add(User user,@Param("token")String token, HttpServletRequest request) {
        Result result = new Result();
        if(token!=null){
            if(tokenService.checkToken(token)){//先验证token
                String passwordMd5 = Md5.md5(user.getPassword());
                String deviceId=tokenService.getDeviceId(token);
                if(deviceId!=null){
                    user.setDeviceId(deviceId);
                    user.setPassword(passwordMd5);
                    try{
                        if (userService.findByUserName(user.getUserName()) == null) {//首先验证用户名是否被注册过
                            if (userService.register(user)) {
                                result.setSuccess(true);
                                result.setMsg("恭喜你，注册成功！");
                            } else {
                                result.setSuccess(false);
                                result.setErrorMsg("很遗憾，注册失败！");
                            }
                        } else {
                            result.setSuccess(false);
                            result.setErrorMsg("该用户名已存在！");
                        }
                    }catch(Exception e){
                        result.setSuccess(false);
                        result.setMsg("数据库异常！");
                        logger.error("用户注册异常",e);
                    }
                }else{
                    result.setSuccess(false);
                    result.setMsg("token异常！");
                }

            }else{
                result.setSuccess(false);
                result.setErrorMsg("token错误或者已经被使用");
            }
        }else{
            result.setSuccess(false);
            result.setErrorMsg("token不能为空");
        }



        return result;
    }
    /**
     * @Author changliang
     * @Description 验证管理员登录，返回用户个数,如果返回‘1’则证明是管理员，否则不是管理员，不允许进入管理员系统,
     * @Date 2019/7/20 20:22
     * @Param user
     * @return result
     **/
    @RequestMapping(value = "/varifyAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result varifyAdmin(User user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Result result = new Result();
        user.setUserStatus("1");
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUserName());
        String password = Md5.md5(user.getPassword());
        map.put("password",password);
        map.put("userstatus",user.getUserStatus());
        try{

            int i = userService.varifyNamePwdAdmin(map);
            if (i==1){
                result.setSuccess(true);
                session.setAttribute("loginUser",user);
                result.setMsg("登陆成功，欢迎您！");
            }else{
                result.setSuccess(false);
                result.setErrorMsg("用户名或密码错误或者你没有权限！");
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setErrorMsg("数据库异常！");
            logger.error("管理员身份验证异常！",e);
        }

        return result;
    }

    /**
     * @Author changliang
     * @Description 退出系统，如果当前无Session，则直接退出，有Session则移除user属性，同时转向登录
     * @Date 2019/7/20 20:22
     * @Param user
     * @return result
     **/
   @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public void exitSystem(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession(false);
        if(session==null)
            return;
        else {
            session.removeAttribute("loginUser");
        }
       try {
           response.sendRedirect("/diaisheng/admin/login");
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
}