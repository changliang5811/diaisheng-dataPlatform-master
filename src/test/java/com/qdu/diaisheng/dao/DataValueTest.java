package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.BaseTest;
import com.qdu.diaisheng.entity.DataValue;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class DataValueTest extends BaseTest {
    @Autowired
    private DataVauleDao dataVauleDao;

    @Test
    @Ignore
    public void testInsertDataValue(){

    }


    @Test
    @Ignore
    public void testQueryByPointId(){

        String dataPointId="30946";
        List<DataValue>dataValueList=dataVauleDao.queryByDataPointId(dataPointId);
        for(DataValue dataValue:dataValueList){
            System.out.println(dataValue.getCreateTime()+" "+dataValue.getValue());
        }



    }

    @Test
    @Ignore
    public void testQueryByDateAndDataPointId(){



    }

    @Test
    @Ignore
    public void testQueryByDate(){
        String date="2019-05-13 05:14:00";
        List<DataValue>dataValueList=dataVauleDao.queryByDate(date);
        for(DataValue dataValue:dataValueList){
            System.out.println(dataValue.getCreateTime()+" "+dataValue.getValue());
        }
    }

    @Test
    @Ignore
    public void testQueryBetweenDateAndPointId(){
        String pointId="32269";
        String date2="2019-05-13 05:26:00";
        String date1="2019-05-13 05:14:00";
        List<DataValue>dataValueList=dataVauleDao.queryBetweenDateAndPonitId(date1,date2,pointId);
        for(DataValue dataValue:dataValueList){
            System.out.println(dataValue.getCreateTime()+" "+dataValue.getValue());

        }
    }




    @Test
    public void  testqueryBetweenDateAtPointIds(){
        String startDate="2019-04-30 00:01:00";
        String endDate="2019-05-01 02:14:0";
        List<String>pointIdList=new ArrayList<>();
        pointIdList.add("41607");
        pointIdList.add("32269");
        List<DataValue> dataValueList=dataVauleDao.queryBetweenDateAtPointIds(startDate,endDate,pointIdList);
        for(DataValue dataValue:dataValueList){
            System.out.println(dataValue.getCreateTime()+" "+dataValue.getValue());
        }
    }


}
