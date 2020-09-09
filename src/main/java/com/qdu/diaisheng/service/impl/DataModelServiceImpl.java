package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.dao.DataModelDao;
import com.qdu.diaisheng.entity.DataModel;
import com.qdu.diaisheng.service.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataModelServiceImpl implements DataModelService {

    @Autowired
    private DataModelDao dataModelDao;

    @Override
    public List<DataModel> getDataModelByDeviceId(String deviceID) {
        return dataModelDao.queryDataModelByDeviceId(deviceID);
    }

    @Override
    public int addDataModel(DataModel dataModel) {
        return dataModelDao.insertDataModel(dataModel);
    }

    @Override
    public int deleteDataModel(int dataModelId) {
        return dataModelDao.deleteDataModel(dataModelId);
    }

    @Override
    public int updateDataModel(String dataModelName,int dataModelId) {
        return dataModelDao.updateDataModel(dataModelName,dataModelId);
    }
}
