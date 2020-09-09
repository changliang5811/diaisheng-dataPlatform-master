package com.qdu.diaisheng.util;


import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5 {
    public static String md5(String inStr) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        /**
        //将字符串转化为字符数组
        char[] charArray = inStr.toCharArray();
        //将字符数组转为字节数组
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        //return hexValue.toString();
         **/
        md5.reset();
        return bytes2HexString(md5.digest(inStr.getBytes()));
    }

    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    // 加密后解密
    public static String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    /**
     * @return
     * @Author changliang
     * @Description 解决遇到了md5签名少0的情况，导致签名也正不通过的情况
     * @Date 2019/7/21 11:36
     * @Param [bytes]
     **/
    private static String bytes2HexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            //方法一
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16) {
                sb.append("0"); //当转换十进制，会忽略掉前面的"0"
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();

    }
}
