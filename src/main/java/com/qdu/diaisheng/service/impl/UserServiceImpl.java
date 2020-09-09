package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.dao.TokenDao;
import com.qdu.diaisheng.dao.UserDao;
import com.qdu.diaisheng.entity.User;
import com.qdu.diaisheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    TokenDao tokenDao;
    @Override
    public int login(String userName, String password) {
       return  userDao.findByUserNameAndPassword(userName,password);
    }

    @Override
    public boolean register(User user) {
        boolean userAdd=userDao.addUser(user);//添加用户
        if(userAdd){//只有在添加用户成功后才更改token状态
            int effectNum=tokenDao.changeStatus();
            if(effectNum>0)
                return true;
            else
                return false;
        }else
            return false;

    }

    @Override
    public User findByUserName(String name) {
        return userDao.findByUserName(name);
    }
    /**
     * @Author changliang
     * @Description 验证管理员登录，返回用户状态
     * @Date 2019/7/20 20:21
     * @Param [map]
     * @return
     **/
    @Override
    public int varifyNamePwdAdmin(Map<String, Object> map) {
        return userDao.varifyNamePwdAdmin(map);
    }
}
