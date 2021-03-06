package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.DataValueEnum;
import com.qdu.diaisheng.dao.DataPointDao;
import com.qdu.diaisheng.dao.DataVauleDao;
import com.qdu.diaisheng.dto.DataValueExecution;
import com.qdu.diaisheng.entity.DataPoint;
import com.qdu.diaisheng.entity.DataValue;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.qdu.diaisheng.service.DataValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DataValueServiceImpl implements DataValueService {

    @Autowired
    private DataVauleDao dataValueDao;

    @Autowired
    private DataPointDao dataPointDao;

    /**
     * 向数据库中添加数据
     * @param dataValue
     * @return
     */
    @Override
    @Transactional
    public DataValueExecution addDataValue(DataValue dataValue) {
        if(dataValue!=null&&dataValue.getDataPoint()!=null&&dataValue.getDataPoint().getDataPointId()!=null){
            int effectedNum=dataValueDao.insertDataVaule(dataValue);
            if (effectedNum<0){
                throw new RuntimeException("插入数据错误");
            }

            return new DataValueExecution(DataValueEnum.SUCCESS,dataValue);

        }else {
            return new DataValueExecution(DataValueEnum.PAR_EMPTY);
        }
    }

    /**
     * 通过数据点Id来查询数据列表
     * @param
     * @return
     */
    /*
    @Override
    public DataValueExecution getDataValueListByPointId(String ponitId) {
        DataValueExecution dve=new DataValueExecution();

        if(ponitId!=null){
            List<DataValue> dataValueList =dataValueDao.queryByDataPointId(ponitId);
            if(dataValueList!=null){
                dve.setDataValueList(dataValueList);
                dve.setCount(dataValueList.size());
                dve.setState(DataValueEnum.SUCCESS.getState());
            }
            else{
                dve.setState(DataValueEnum.EMPTY.getState());
            }
        }else{
            return new DataValueExecution(DataValueEnum.PAR_EMPTY);
        }
        return dve;
    }
 */

    @Override
    public DataValueExecution getnowdate(String deviceId) throws ParseException {

        List<DataValue>dataValueList=new ArrayList<>();
        DataValueExecution dve=new DataValueExecution();
        List<DataPoint> dataPointList=dataPointDao.getDataPointbyDevice(deviceId);

        List<String> ds = new ArrayList<>();
        if(dataPointList!=null){
            for(DataPoint dataPoint:dataPointList){
                String pointId = dataPoint.getDataPointId();
                if (pointId.equals("41607")||pointId.equals("41608")||pointId.equals("41610"))continue;
                ds.add(dataPoint.getDataPointId());
            }
            dataValueList=dataValueDao.getnowdate(ds);
        }
        else{
            dve.setState(DataValueEnum.EMPTY.getState());
        }
        if(dataValueList!=null){
            for(DataValue dataValue:dataValueList){
                String s = dataValue.getCreateTime();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date nowTime=new Date();
                String ss = s.substring(0,19);
                dataValue.setCreateTime(ss);
                Date dataDate=sdf.parse(ss);
                long end  = nowTime.getTime();
                long diff=end - dataDate.getTime();
                float day= diff / (24 * 60 * 60 * 1000);
                if(day>=1){
                    dataValue.setRed(1);
                }else{
                    dataValue.setRed(0);
                }
            }
            dve.setDataValueList(dataValueList);
            dve.setState(DataValueEnum.SUCCESS.getState());
        }else{
            dve.setState(DataValueEnum.EMPTY.getState());
        }
        return dve;
    }
/**
* description: 获取当前deviceId的关键数据
* @author changliang
* @Date 2020/10/20 00:37
* @methodName getnowKeydata
* @return DataValueExecution
* @param deviceId
*/
    @Override
    public DataValueExecution getnowKeyData(String deviceId) throws ParseException {
        List<DataValue>dataValueList=new ArrayList<>();
        DataValueExecution dve=new DataValueExecution();

        //List<DataPoint> dataPointList=dataPointDao.getDataPointbyDevice(deviceId);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> ds = new ArrayList<>();
        String[] data_codes = new String[]{"32280","30949","51284","32269","32270","90004","63007","73007","83007"};
        /*if(dataPointList!=null){
            for(DataPoint dataPoint:dataPointList){
                String pointId = dataPoint.getDataPointId();
                if (Arrays.asList(data_codes).contains(pointId)){
                    ds.add(dataPoint.getDataPointId());
                }
            }*/
            ds = Arrays.asList(data_codes);
            dataValueList=dataValueDao.getnowdate(ds);
            //处理24小时出水量等数据
            for (int i = 0;i<3;i++){
                DataValue dataValue_item = dataValueList.get(i);
                String data_code_item = ds.get(i);
                Float now_data_value = dataValue_item.getValue();
                String date_sql_new = dataValue_item.getCreateTime();
                DataValue dataValueBefore = null;
                int minutes = -5;
                String date_end = null;
                String date_start = null;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(date_sql_new));
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                while (dataValueBefore == null){
                    //数据库最新时间-24小时
                    date_end = sdf.format(calendar.getTime());
                    calendar.add(Calendar.MINUTE,minutes);
                    date_start = sdf.format(calendar.getTime());
                    dataValueBefore = dataValueDao.getBeforeKeyData(date_start,date_end,data_code_item);
                    minutes -= 5;
                }
                dataValue_item.getDataPoint().setDataPointName("24小时"+dataValue_item.getDataPoint().getDataPointName());
                dataValue_item.setValue(now_data_value - dataValueBefore.getValue());

            }
        //}
        /*else{
            dve.setState(DataValueEnum.EMPTY.getState());
        }*/
        if(dataValueList != null){
            for(DataValue dataValue:dataValueList){
                String s = dataValue.getCreateTime();
                Date nowTime=new Date();
                String ss = s.substring(0,19);
                dataValue.setCreateTime(ss);
                Date dataDate=sdf.parse(ss);
                long end  = nowTime.getTime();
                long diff=end - dataDate.getTime();
                float day= diff / (24 * 60 * 60 * 1000);
                if(day>=1){
                    dataValue.setRed(1);
                }else{
                    dataValue.setRed(0);
                }
            }
            dve.setDataValueList(dataValueList);
            dve.setState(DataValueEnum.SUCCESS.getState());
        }else{
            dve.setState(DataValueEnum.EMPTY.getState());
        }
        return dve;
    }

    /**
     *
     * 通过日期来获取数据
     * @param date
     * @return
     */
    @Override
    public List<DataValue> getDataValueListByDate(String date) {
        return dataValueDao.queryByDate(date);
    }

    /**
     *
     * 通过数据点Id来获取某个数据点下两个日期之间的数据
     * @param
     * @param date1
     * @param date2
     * @return
     */
    @Override
    public DataValueExecution getDateValueListAtPointIdBetweenDate(String date1,String date2,List<String>dataPointIds) {
        DataValueExecution dve=new DataValueExecution();

        if(date1!=null&&date2!=null&&dataPointIds!=null&&dataPointIds.size()>0){
            List<DataValue> dataValueList =dataValueDao.queryBetweenDateAtPointIds(date1,date2,dataPointIds);
            if(dataValueList!=null){
                dve.setDataValueList(dataValueList);
                dve.setCount(dataValueList.size());
                dve.setState(DataValueEnum.SUCCESS.getState());
            }
            else{
                dve.setState(DataValueEnum.EMPTY.getState());
            }
        }else{
            return new DataValueExecution(DataValueEnum.PAR_EMPTY);
        }
        return dve;
    }



    @Override
    public DataValueExecution getDataValueByDataPoint(String dataPointId) {
        DataValueExecution dve=new DataValueExecution();

        if(dataPointId!=null){
            DataValue dataValue=dataValueDao.getDataByPointId(dataPointId);
            if(dataValue!=null){
                dve.setDataValue(dataValue);
                dve.setState(DataValueEnum.SUCCESS.getState());
            }else{
                dve.setState(DataValueEnum.EMPTY.getState());
            }
        }else{
            dve.setState(DataValueEnum.PAR_EMPTY.getState());
        }
        return dve;
    }

}
