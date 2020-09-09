package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.DataPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataPointDao {
    int insertDataPoint(DataPoint dataPoint);
    List<DataPoint> queryDataPointListByDataModel(Integer dataModelId);//通过modelid查询数据点
    String getPointNameByPointId(String pointId);
    int deleteDataPoint(String dataPonitId);
    int updateDataPoint(@Param("dataPoint")DataPoint dataPoint,
                        @Param("dataPointId")String dataPointId);
    List<DataPoint> getDataPointbyDevice(String deviceId);
    int getDataPointCount(String deviceId);
}
