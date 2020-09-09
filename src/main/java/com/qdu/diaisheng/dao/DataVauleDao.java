package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.DataValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface DataVauleDao {
    int insertDataVaule(DataValue dataValue);
    List<DataValue> queryByDataPointId(String dataPointId);//通过数据点查询数据
    DataValue queryByDateAndPointID(@Param("date") String date,
                                    @Param("dataPointId")String pointId);//通过日期和数据点来查询数据
    List<DataValue> queryByDate( String date);//在所有数据点中通过日期来查询数据
    List<DataValue> queryBetweenDateAndPonitId(@Param("date1") String date1,
                                               @Param("date2") String date2,
                                               @Param("dataPointId") String dataPointId);//在一个数据点中查询两个日期之间的数据

    List<DataValue> getnowdate(@Param("dataPointIds") List<String> dataPointId);//获取最新的数据


    List<DataValue> queryBetweenDateAtPointIds(@Param("date1") String date1,
                                               @Param("date2") String date2,
                                               @Param("dataPointIds") List<String> dataPointId);//在多个数据点查询两个日期之间的数据

    DataValue getDataByPointId(String dataPointId);//通过数据点查询最新的数据

}
