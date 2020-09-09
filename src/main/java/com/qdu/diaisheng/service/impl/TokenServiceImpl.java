package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.dao.TokenDao;
import com.qdu.diaisheng.entity.Token;
import com.qdu.diaisheng.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenDao tokenDao;

    @Override
    public boolean checkToken(String token) {
         if(tokenDao.checkToken(token)&&(tokenDao.getTokenStatus(token)==0)){//如果token存在并且token没有被使用
             return  true;
         }else {
             return false;
         }
    }

    @Override
    public String getDeviceId(String token) {
        return tokenDao.getDeviceIdByToken(token);
    }

    @Override
    public boolean addToken(Token token) {
        if(token!=null){
            int effectNum=tokenDao.addToken(token);
            if(effectNum<0)
                return false;
            else
                return true;
        }else{
            return false;
        }
    }


}
