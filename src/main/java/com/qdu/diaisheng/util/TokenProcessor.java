package com.qdu.diaisheng.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import sun.misc.BASE64Encoder;

//令牌生产器
public class TokenProcessor {
    private TokenProcessor(){}
    private static TokenProcessor instance = new TokenProcessor();
    public static TokenProcessor getInstance(){
        return instance;
    }
    public String generateTokeCode(){
        String value = System.currentTimeMillis()+new Random().nextInt()+"";
        System.out.println(value);


        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(currentTime);
        System.out.println(formatter.format(date));


        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
            System.out.println(be.encode(b));
            return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        TokenProcessor processor=new TokenProcessor();
        processor.generateTokeCode();
    }
}