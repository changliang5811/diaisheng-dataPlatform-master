package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.DataModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataModelDao {
    int insertDataModel(DataModel dataModel);
    List<DataModel> queryDataModelByDeviceId(String Device);
    int deleteDataModel(int dataModelId);
    int updateDataModel(@Param("dataModelName") String dataModelName,
                        @Param("dataModelId") int dataModelId);
}
