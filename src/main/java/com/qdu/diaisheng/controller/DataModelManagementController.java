package com.qdu.diaisheng.controller;


import com.qdu.diaisheng.entity.DataModel;
import com.qdu.diaisheng.service.DataModelService;
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
/**
 * @Autor wangxi
 * @Description 数据模板controller
 * @Date 2019/7/22
 */
@Controller
@RequestMapping("/modeladmin")
public class DataModelManagementController {

    @Autowired
    private DataModelService dataModelService;


    /**
     * @author wangxi
     * @Description 根据数据id获取数据模板
     * 通过modelMap返回数据
     * @date  2019/7/23
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdatamodel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDataModel(String deviceId,HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();
        if(deviceId!=null){
            List<DataModel> dataModelList=dataModelService.getDataModelByDeviceId(deviceId);
            if(dataModelList!=null&&dataModelList.size()>0){
                modelMap.put("success",true);
                modelMap.put("dataModelList",dataModelList);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg", "数据模板为空");
            }

        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","设备为空");
        }
        return modelMap;
    }



    @RequestMapping(value = "/adddatamodel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addDataModel(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();
        String deviceId= HttpServletUtil.getString(request,"deviceId");
        if(deviceId!=null){
            List<DataModel> dataModelList=dataModelService.getDataModelByDeviceId(deviceId);
            if(dataModelList!=null&&dataModelList.size()>0){
                modelMap.put("success",true);
                modelMap.put("dataModelList",dataModelList);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg", "数据模板为空");
            }

        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","设备为空");
        }
        return modelMap;
    }


    @RequestMapping(value = "/deletedatamodel",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteDataModel(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        int modelId=HttpServletUtil.getInt(request,"dataModelId");
        if(modelId>0){
           int effectNum=dataModelService.deleteDataModel(modelId);
           if (effectNum>0){
               modelMap.put("success",true);
           }else{
               modelMap.put("success",false);
               modelMap.put("errMsg","删除数据模板出错");
           }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","未找到数据模板");
        }

        return modelMap;
    }


    @RequestMapping(value = "updatedatamodel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updatedatamodel(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<>();
        String newName=HttpServletUtil.getString(request,"modelName");
        int modelId=HttpServletUtil.getInt(request,"modelId");
        if(newName!=null&&modelId>0){
            int effectedNum=dataModelService.updateDataModel(newName,modelId);
            if(effectedNum>0){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","修改数据模板出错");
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","数据模板的名字为空或者未找到数据模板");
        }
        return modelMap;
    }

}
