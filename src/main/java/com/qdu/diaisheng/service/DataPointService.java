package com.qdu.diaisheng.service;

import com.qdu.diaisheng.entity.DataPoint;
import java.util.List;

import com.qdu.diaisheng.entity.Device;


public interface DataPointService {

    List<DataPoint> getDataPointListByDataModelId(int dataModelId);

    String getDataPointName(String pointId);

    int deleteDataPoint(String dataPointId);

    int editdataPoint(String dataPonitId,DataPoint dataPoint);

    List<DataPoint> getDataPointByDevice(String deviceId);

    int getDataPointCount(List<Device> deviceList);
}
