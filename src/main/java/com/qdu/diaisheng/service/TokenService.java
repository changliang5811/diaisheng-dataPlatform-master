package com.qdu.diaisheng.service;

import com.qdu.diaisheng.entity.Token;

public interface TokenService {
    public boolean checkToken(String token);
    public String getDeviceId(String token);
    public boolean addToken(Token token);
}
