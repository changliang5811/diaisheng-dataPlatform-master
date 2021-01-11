package com.qdu.diaisheng.controller;

/**
 * @Autor wangxi
 * @Description 数据点controller
 * @Date 2019/7/22
 *
 */
import com.qdu.diaisheng.DataValueEnum;
import com.qdu.diaisheng.dto.DataValueExecution;
import com.qdu.diaisheng.entity.DataValue;
import com.qdu.diaisheng.entity.PageInfo;
import com.qdu.diaisheng.service.DataModelService;
import com.qdu.diaisheng.service.DataPointService;
import com.qdu.diaisheng.service.DataValueService;
import com.qdu.diaisheng.service.DeviceService;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/valueadmin")
public class DataValueManagementController {
    @Autowired
    private DataValueService dataValueService;
    @Autowired
    private DataModelService dataModelService;
    @Autowired
    private DataPointService dataPointService;
    @Autowired
    private DeviceService device;
    private Logger logger = Logger.getLogger(this.getClass());



    /**
     * @author wangxi
     * @Description
     * @date  2017/7/20
     * @Description 获取最新的数据，然后返回给前端
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdata")
    @ResponseBody
    public PageInfo getData(HttpServletRequest request){
        //Integer page, Integer limit
        PageInfo pageInfo = new PageInfo();
        //Map<String,Object> modelMap = new HashMap<>();
        List<DataValue> list = new ArrayList<>();
        String deviceId=request.getParameter("deviceId");
        if(deviceId!=null){
            try{
                //PageHelper pageHelper = new PageHelper();
                // pageHelper.startPage(page, limit);
                DataValueExecution dve=dataValueService.getnowdate(deviceId);
                list = dve.getDataValueList();
                //int total = (int) new com.github.pagehelper.PageInfo<>(list).getTotal();
                if(dve.getState()==DataValueEnum.SUCCESS.getState()){
                    //modelMap.put("data",dve.getDataValueList());
                    pageInfo.setData(dve.getDataValueList());
                    pageInfo.setCode(0);
                    //pageInfo.setCount(total);
                   pageInfo.setMsg("查询数据点信息成功");
                    //modelMap.put("success",true);
                }else{
                    //modelMap.put("success",false);
                    pageInfo.setMsg("查询数据点信息失败");
                }
            }catch (Exception e){
                logger.error("查询数据点信息失败！",e);
                pageInfo.setMsg("查询数据点信息失败");
                //modelMap.put("success",false);
            }
        }else {
            logger.error("查询数据点信息失败！");
            //modelMap.put("success",false);
            pageInfo.setMsg("查询数据点信息失败");

        }
        //Map<String,Object>modelMap=new HashMap<>();


        return pageInfo;

    }

    /**
    * description:
    * @author changliang
    * @Date 2020/10/19 18:52
    * @methodName getKeyData
    * @return PageInfo
    * @param request
    */
    @RequestMapping(value = "/getKeyData")
    @ResponseBody
    public PageInfo getKeyData(HttpServletRequest request){
        //Integer page, Integer limit
        PageInfo pageInfo = new PageInfo();
        List<DataValue> list = new ArrayList<>();
        String deviceId=request.getParameter("deviceId");
        if(deviceId!=null){
            try{
                DataValueExecution dve=dataValueService.getnowKeyData(deviceId);
                list = dve.getDataValueList();
                if(dve.getState()==DataValueEnum.SUCCESS.getState()){
                    pageInfo.setData(dve.getDataValueList());
                    pageInfo.setCode(0);
                    pageInfo.setMsg("查询数据点信息成功");
                }else{
                    pageInfo.setMsg("查询数据点信息失败");
                }
            }catch (Exception e){
                logger.error("查询数据点信息失败！",e);
                pageInfo.setMsg("查询数据点信息失败");
            }
        }else {
            logger.error("查询数据点信息失败！");
            pageInfo.setMsg("查询数据点信息失败");

        }
        return pageInfo;

    }


    /**
     * @author wangxi
     * @Description
     * @date  2017/7/20
     * @Description 前端主动获取数据，前端输入数据点id，返回该数据点的最新数据
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdatabypoint",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>getDataByPoint(String x){
        Map<String,Object>modelMap=new HashMap<>();
        DataValueExecution dve=dataValueService.getDataValueByDataPoint(x);
        if(dve.getState()==DataValueEnum.SUCCESS.getState()){
            modelMap.put("dataValue",dve.getDataValue());
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }
        return modelMap;
    }




    /**
     * @author wangxi
     * @Description
     * @date  2017/7/20
     * @Description 获取前端的数据id,开始时间，结束时间，然后返回某一数据点在某一时间段的数据
     * @return Map
     * @throws
     * @since
     */
    @RequestMapping(value = "/getdatabetweendate",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDataValueBetweenDate(String[] data,String stime,String etime){
        Map<String,Object> modelMap=new HashMap<String, Object>();
        if(data!=null&&etime!=null&&etime!=null){
            stime=stime.replace("T"," ");
           etime=etime.replace("T"," ");
            DataValueExecution dve=dataValueService.getDateValueListAtPointIdBetweenDate(stime,etime, Arrays.asList(data));
            if(dve.getState()== DataValueEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("dataValue",dve.getDataValueList());
                logger.info("历史记录查询成功");
            }else{
                logger.error("历史记录查询失败！");
                modelMap.put("success",false);
                modelMap.put("errMsg","请选择正确的设备、从机、数据点以及日期！");
            }
        }else{
            logger.error("历史记录查询失败！pointId为空或者日期为空");
            modelMap.put("success",false);
            modelMap.put("errMsg","数据点为空或者日期为空");
        }
        return modelMap;
    }


    /**
     * @author wangxi
     * @Description
     * @date  2017/7/20
     * @Description 下载数据 获取前端的数据id,开始时间，结束时间，然后返回某一数据点在某一时间段的数据
     * @return Map
     * @throws
     * @since
     */
        @RequestMapping(value = "/downLoadExcel",method =RequestMethod.GET)
        public void downLoadExcel(String[] data,String stime,String etime, HttpServletResponse response) throws IOException{
            stime=stime.replace("T"," ");
            etime=etime.replace("T"," ");
            DataValueExecution dve = dataValueService.getDateValueListAtPointIdBetweenDate(stime,etime,Arrays.asList(data));
            if(dve.getState()==DataValueEnum.SUCCESS.getState()){
                List<DataValue>dataValueList=dve.getDataValueList();
                if ( dataValueList!= null && dataValueList.size() > 0) {

                    String fileName = "value.xlsx";
                    response.setHeader("Content-disposition", "attachment;filename="
                            + new String(fileName.getBytes("gb2312"), "ISO8859-1"));//设置文件头编码格式
                    response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
                    response.setHeader("Cache-Control", "no-cache");//设置头
                    response.setDateHeader("Expires", 0);//设置日期头

                    XSSFWorkbook workbook = new XSSFWorkbook();

                    XSSFSheet sheet = workbook.createSheet();
                    CellStyle cellStyle = workbook.createCellStyle();

                    cellStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

                    Row header=sheet.createRow(0);
                    for(int cellNum=0;cellNum<4;cellNum++){
                        Cell cell=header.createCell(cellNum);

                        if(cellNum==0){
                            cell.setCellValue("pointName");
                        }else if(cellNum==1){
                            cell.setCellValue("pointId");
                        }else if(cellNum==2){
                            cell.setCellValue("createTime");
                        }else{
                            cell.setCellValue("value");
                        }

                    }

                    int rowNum = 1;
                    for (DataValue dataValue:dataValueList) {
                        Row row = sheet.createRow(rowNum);

                        Cell cell=row.createCell(0);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(dataValue.getDataPoint().getDataPointName());

                        Cell cell1 = row.createCell(1);
                        cell1.setCellStyle(cellStyle);
                        cell1.setCellValue(dataValue.getDataPoint().getDataPointId());

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(dataValue.getCreateTime());

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(dataValue.getValue());

                        rowNum++;
                    }

                    workbook.write(response.getOutputStream());

                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
        }






}
