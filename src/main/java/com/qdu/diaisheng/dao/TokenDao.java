package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.Token;

public interface TokenDao {
    public boolean checkToken(String token);//验证是否存在该token
    public Integer changeStatus();//更改token的状态为1
    public Integer getTokenStatus(String token);//获取token的状态
    public String getDeviceIdByToken(String token);//通过token获取deviceId；
    public Integer addToken(Token token);
}
