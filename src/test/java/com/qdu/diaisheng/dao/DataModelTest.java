package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.BaseTest;
import com.qdu.diaisheng.entity.DataModel;
import com.qdu.diaisheng.entity.Device;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataModelTest extends BaseTest {
    @Autowired
    private DataModelDao dataModelDao;

    @Test
    @Ignore
    public void testInsertDataModel(){
        DataModel dataModel=new DataModel();
        dataModel.setDataModelName("test");
        Device device=new Device();
        device.setDeviceId("00015203000000000001");
        dataModel.setDevice(device);
        dataModel.setCreateTime("2018-10-30 16:30:24.0");
        int effectedNum=dataModelDao.insertDataModel(dataModel);
        System.out.println(effectedNum);
    }

    @Test
    public void testQueryDataModelByDeviceId(){
        String deviceId="00015203000000000001";
        List<DataModel>dataModelList=dataModelDao.queryDataModelByDeviceId(deviceId);
        for(DataModel dataModel:dataModelList){
            System.out.println(dataModel);
        }
    }

    @Test
    @Ignore
    public void testDeleteDataModel(){
        int dataModelId=8;
        int effectedNum=dataModelDao.deleteDataModel(dataModelId);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateDataModel(){
        String dataModelName="ts";
        int effectedNum=dataModelDao.updateDataModel(dataModelName,8);
        System.out.println(effectedNum);

    }


}
