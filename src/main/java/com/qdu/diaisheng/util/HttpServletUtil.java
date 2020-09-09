package com.qdu.diaisheng.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtil {
    public static int getInt(HttpServletRequest request,String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request,String key){
        try {
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static Float getFloat(HttpServletRequest request,String key){
        try{
            return Float.valueOf(request.getParameter(key));
        }
        catch (Exception e){
            return -1F;
        }

    }


    public static Boolean getBoolean(HttpServletRequest request,String key){
        try{
            return Boolean.valueOf(request.getParameter(key));
        }
        catch (Exception e){
            return false;
        }

    }

    public static String getString(HttpServletRequest request,String key){
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if ("".equals(result))
                request = null;
            return result;
        }
        catch (Exception e){
            return null;
        }
    }




}
