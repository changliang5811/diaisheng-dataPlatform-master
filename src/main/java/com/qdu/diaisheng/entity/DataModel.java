package com.qdu.diaisheng.entity;

//数据模型实体类
public class DataModel {
    private Integer dataModelId;//数据模型Id
    private String dataModelName;//数据模型名
    private Device device;//关联设备
    private String createTime;//创建时间
    private String lastEditTime;//最近一次的修改时间
    private Integer dataPointCount;//关联数据点的数量

    public Integer getDataPointCount() {
        return dataPointCount;
    }

    public void setDataPointCount(Integer dataPointCount) {
        this.dataPointCount = dataPointCount;
    }

    public Integer getDataModelId() {
        return dataModelId;
    }

    public void setDataModelId(Integer dataModelId) {
        this.dataModelId = dataModelId;
    }

    public String getDataModelName() {
        return dataModelName;
    }

    public void setDataModelName(String dataModelName) {
        this.dataModelName = dataModelName;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
