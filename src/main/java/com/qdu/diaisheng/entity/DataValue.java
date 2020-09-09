package com.qdu.diaisheng.entity;

public class DataValue{
    private int dataValueId;//数据id
    private DataPoint dataPoint;//数据点
    private String createTime;
    private Float value;
    private Integer red;//0 不标红 1标红

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    @Override
    public String toString() {
        return "DataValue{" +
                "dataValueId='" + dataValueId + '\'' +
                ", dataPoint=" + dataPoint +
                ", createTime=" + createTime +
                ", value=" + value +
                '}';
    }


    public int getDataValueId() {
        return dataValueId;
    }

    public void setDataValueId(int dataValueId) {
        this.dataValueId = dataValueId;
    }

    public DataPoint getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
