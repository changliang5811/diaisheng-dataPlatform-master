package com.qdu.diaisheng.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ImageUtil {

    public static boolean Base64ToImage(String imgStr){
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){//调整异常数据
                    b[i]+=256;
                }
            }
            //生成png图片
            String imgFilePath = "D:\\photo\\new_timg.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
