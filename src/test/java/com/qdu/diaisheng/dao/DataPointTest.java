package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.BaseTest;
import com.qdu.diaisheng.entity.DataModel;
import com.qdu.diaisheng.entity.DataPoint;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DataPointTest extends BaseTest {
    @Autowired
    private DataPointDao dataPointDao;
    @Test
    @Ignore
    public void testInsetDataPoint(){
        DataPoint dataPoint=new DataPoint();
        dataPoint.setDataPointId("374300");
        DataModel dataModel=new DataModel();
        dataModel.setDataModelId(1);
        dataPoint.setDataModel(dataModel);
        dataPoint.setDataPointName("controller");
        dataPoint.setDataType(0);
        dataPoint.setDataPointRegister("Ox4000f");
        dataPoint.setPower(0);
        dataPoint.setDataValueType("int");

        //dataPoint.setCreateTime(new Date());
        int effectedNum=dataPointDao.insertDataPoint(dataPoint);
        System.out.println(effectedNum);

    }

    @Test
    @Ignore
    public void testQueryDataPointListByDataModel(){
        int dataModelId=1;
      List<DataPoint> dataPointList = dataPointDao.queryDataPointListByDataModel(dataModelId);
      for(DataPoint dataPoint:dataPointList){
          System.out.println(dataPoint);
      }
    }

    @Test
    public void testDataPointCount(){
        String deviceId="00015203000000000001";
        int count=dataPointDao.getDataPointCount(deviceId);
        System.out.println(count);
    }
}
