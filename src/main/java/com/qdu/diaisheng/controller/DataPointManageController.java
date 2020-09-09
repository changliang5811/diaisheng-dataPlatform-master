package com.qdu.diaisheng.controller;

import com.qdu.diaisheng.entity.DataPoint;
import com.qdu.diaisheng.service.DataModelService;
import com.qdu.diaisheng.service.DataPointService;
import com.qdu.diaisheng.util.HttpServletUtil;
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
@RequestMapping("/datapointadmin")
public class DataPointManageController {



    @Autowired
    private DataPointService dataPointService;

    @Autowired
    private DataModelService dataModelService;

    /**
     * @author wangxi
     * @Description 根据modelid获取数据点
     * 通过modelMap返回数据
     * @date  2019/8/5
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdatapoint",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDataPoint(String dataModelId,HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();
        int modelId=Integer.parseInt(dataModelId);
        if(modelId>0){
            List<DataPoint> dataPointList=dataPointService.getDataPointListByDataModelId(modelId);
            if(dataPointList!=null&&dataPointList.size()>0){
                modelMap.put("success",true);
                modelMap.put("dataPointList",dataPointList);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","数据点为空");
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","数据模型id错误");
        }

        return modelMap;

    }


    @RequestMapping(value = "/getpointbydevice",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getPointByDevice(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();
        String deviceId=HttpServletUtil.getString(request,"deviceId");
        if(deviceId!=null){
            List<DataPoint>dataPointList=dataPointService.getDataPointByDevice(deviceId);
            if(dataPointList!=null&&dataPointList.size()>0){
                modelMap.put("dataPointList",dataPointList);
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","数据点为空");
            }

        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","设备为空");
        }

        return modelMap;

    }

        @RequestMapping(value = "/deletedatapoint",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>deleteDataPoint(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();
        String dataPointId=HttpServletUtil.getString(request,"dataPointId");
        if(dataPointId!=null){
            int effectedNum=dataPointService.deleteDataPoint(dataPointId);
            if(effectedNum>0){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","删除数据点失败");
            }

        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","数据点为空");
        }
        return modelMap;
    }


}
