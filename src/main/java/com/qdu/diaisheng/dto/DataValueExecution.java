package com.qdu.diaisheng.dto;

import com.qdu.diaisheng.DataValueEnum;
import com.qdu.diaisheng.entity.DataValue;
import java.util.List;

public class DataValueExecution {
    private int state;

    private String stateInfo;

    private  List<DataValue> dataValueList;

    private DataValue dataValue;

    private Integer count;


    public DataValue getDataValue() {
        return dataValue;
    }

    public void setDataValue(DataValue dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DataValue> getDataValueList() {
        return dataValueList;
    }

    public void setDataValueList(List<DataValue> dataValueList) {
        this.dataValueList = dataValueList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public DataValueExecution(){

    }

    //失败的构造器
    public DataValueExecution(DataValueEnum dataValueEnum){
        this.state=dataValueEnum.getState();
        this.stateInfo=dataValueEnum.getStateInfo();
    }
    //成功的构造器
    public DataValueExecution(DataValueEnum dataValueEnum,DataValue dataValue){
        this.state=dataValueEnum.getState();
        this.stateInfo=dataValueEnum.getStateInfo();
        this.dataValue=dataValue;
    }
    //成功的构造器
    public DataValueExecution(DataValueEnum dataValueEnum,List<DataValue> dataValueList){
        this.state=dataValueEnum.getState();
        this.stateInfo=dataValueEnum.getStateInfo();
        this.dataValueList=dataValueList;
    }
}
