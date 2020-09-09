package com.qdu.diaisheng.controller;


import com.qdu.diaisheng.dao.DeviceDao;
import com.qdu.diaisheng.entity.Device;
import com.qdu.diaisheng.entity.User;
import com.qdu.diaisheng.service.DeviceService;
import com.qdu.diaisheng.util.HttpServletUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/deviceadmin")
public class DeviceManagementController {

    //"diaisheng/deviceadmin/getdevice"

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDao deviceDao;

    @RequestMapping(value = "/adddevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addDevice(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        Map<String,Object>modelMap=new HashMap<>();
        if(user==null){
            modelMap.put("success",false);
            modelMap.put("redirect",true);
            return modelMap;
        }else{
            Device device=new Device();
            String deviceId=HttpServletUtil.getString(request,"deviceId");
            int userId=user.getUserId();
            String deviceName=HttpServletUtil.getString(request,"deviceName");
            String createTime=new Date().toString();
            if(device!=null&&deviceName!=null&&userId>0){
                device.setDeviceId(deviceId);
                device.setDeviceName(deviceName);
                device.setCreateTime(createTime);
                device.setUserId(userId);
                int effectedNum=deviceDao.insertDevice(device);
                if(effectedNum>0){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                }
               // device.set
            }

        }
        return modelMap;
    }


    /**
     * @author wangxi
     * @Description 根据user_id获取设备
     * 通过modelMap返回数据
     * @date  2019/8/4
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdevice",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDevice(HttpServletRequest request){

        User user=(User) request.getSession().getAttribute("loginUser");
        Map<String,Object>modelMap=new HashMap<>();
        if(user==null){
            modelMap.put("success",false);
            modelMap.put("redirect","/diaisheng/admin/login");
            return modelMap;
        }else{
            List<Device> deviceList=deviceService.getDeviceList(user);
            if(deviceList!=null&&deviceList.size()>0){
                modelMap.put("success",true);
                modelMap.put("device",deviceList);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","没有查到设备");
            }

        }


        return modelMap;
    }

    /**
     * @author changliang
     * @Description 根据user_id获取设备
     * 通过modelMap返回数据
     * @date  2019/8/4
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getDeviceWechat",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDeviceWechat(String userName){

       // User user=(User) request.getSession().getAttribute("loginUser");
        Map<String,Object>modelMap=new HashMap<>();
        if(userName==null){
            modelMap.put("success",false);
            modelMap.put("redirect","/diaisheng/admin/login");
            return modelMap;
        }else{
            List<Device> deviceList=deviceService.getDeviceListByName(userName);
            if(deviceList!=null&&deviceList.size()>0){
                modelMap.put("success",true);
                modelMap.put("device",deviceList);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","没有查到设备");
            }

        }


        return modelMap;
    }


    /**
     * @author wangxi
     * @Description 根据user_id获取在线设备数
     * 通过modelMap返回数据
     * @date  2019/8/4
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getonlinedevice",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>getOnlineDevice(HttpServletRequest request){

        User user=(User) request.getSession().getAttribute("loginUser");
        Map<String,Object>modelMap=new HashMap<>();
        if(user==null){
            modelMap.put("success",false);
            modelMap.put("redirect","/diaisheng/admin/login");
            return modelMap;
        }else{
            List<Device> deviceList=deviceService.getDeviceList(user);
            if(deviceList!=null&&deviceList.size()>0){
                int online=0;
                for(Device device:deviceList){
                    if(device.getDeviceStatus()==1){
                        online++;
                    }
                }
                int offline=deviceList.size()-online;
                modelMap.put("online",online);
                modelMap.put("offline",offline);
            }

           else{
                modelMap.put("success",false);
                modelMap.put("errMsg","没有查到设备");
            }

        }


        return modelMap;
    }

    /**
     * @author wangxi
     * @Description 获取基站的位置信息
     * @date  2019/8/28
     * @return 返回JSON数据
     * errcode
     * 0: 成功
     * 10000: 参数错误
     * 10001: 无查询结果
     * @throws
     * @since
     */
    @RequestMapping(value = "/getlocation",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDeviceLoaction(HttpServletRequest request){
        String res=null;
        Map<String,Object>map=new HashMap<>();
        User user=(User) request.getSession().getAttribute("loginUser");
        Map<String,Object>modelMap=new HashMap<>();
        if(user==null){
            modelMap.put("success",false);
            modelMap.put("redirect","/diaisheng/admin/login");
            return modelMap;
        }else{
            List<Device> deviceList=deviceService.getDeviceList(user);
            if(deviceList!=null&&deviceList.size()>0){//目前仅支持一个设备
                Device device=deviceList.get(0);
                map.put("lon",device.getLon());
                map.put("lat",device.getLat());
                map.put("success",true);
            }
            else{
                modelMap.put("success",false);
                modelMap.put("errMsg","没有查到设备");
            }
        }
        return map;
    }




}
