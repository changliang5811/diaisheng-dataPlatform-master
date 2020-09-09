package com.qdu.diaisheng.controller;

import com.qdu.diaisheng.entity.Device;
import com.qdu.diaisheng.entity.User;
import com.qdu.diaisheng.service.DataPointService;
import com.qdu.diaisheng.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/indexadmin")
public class IndexManagerController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DataPointService dataPointService;

    /**
     * @Author wangxi
     * @Description 获取主页的数据
     * @Date 2019/9/21 17:38
     * @Param []
     * @return deviceCnt 设备数
     *         pointCnt 数据点数
     **/
    @RequestMapping(value = "/getdata",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getData(HttpServletRequest request){
        Map<String,Object>data= new HashMap<String,Object>();
        User user= (User) request.getSession().getAttribute("loginUser");
        List<Device> deviceList=deviceService.getDeviceList(user);
        int deviceCount=deviceList.size();
        int pointCnt=dataPointService.getDataPointCount(deviceList);
        if(deviceCount>-1&pointCnt>-1){
            data.put("deviceCnt",deviceCount);
            data.put("dataPointCnt",pointCnt);
            data.put("success",true);
        }else {
            data.put("success",false);
            data.put("errMsg","输入信息错误");
        }
        return data;

    }

}
