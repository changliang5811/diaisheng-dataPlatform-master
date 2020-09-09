package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenDao extends BaseTest {
   @Autowired
   private TokenDao tokenDao;


    @Test
    public void testToken(){
        String token="273fjn3j92mk";
        System.out.println();
    }

}
