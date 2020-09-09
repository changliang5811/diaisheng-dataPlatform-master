package com.qdu.diaisheng.entity;

//数据点实体类
public class DataPoint {
    private String dataPointId;//数据点ID
    private DataModel dataModel;//关联数据模型
    private String dataPointName;
    private String dataPointRegister;//数据点关联寄存器
    private Integer dataType;//数据类型类别 0：数值型 1：开关型 2：字符型p
    private String dataValueType;//数据值类型
    private Integer power;//权限 0：只读 1：只写 2；读写
    private String dataPointUnit;//数据单位
    private String varDesc;//变量描述
    private String dataFormual;//公式
    private String dataReverseFormual;//反向公式
    private String createTime;
    private String lastEditTime;

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDataPointId() {
        return dataPointId;
    }

    public void setDataPointId(String dataPointId) {
        this.dataPointId = dataPointId;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public String getDataPointName() {
        return dataPointName;
    }

    public void setDataPointName(String dataPointName) {
        this.dataPointName = dataPointName;
    }

    public String getDataPointRegister() {
        return dataPointRegister;
    }

    public void setDataPointRegister(String dataPointRegister) {
        this.dataPointRegister = dataPointRegister;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataValueType() {
        return dataValueType;
    }

    public void setDataValueType(String dataValueType) {
        this.dataValueType = dataValueType;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getDataPointUnit() {
        return dataPointUnit;
    }

    public void setDataPointUnit(String dataPointUnit) {
        this.dataPointUnit = dataPointUnit;
    }

    public String getVarDesc() {
        return varDesc;
    }

    public void setVarDesc(String varDesc) {
        this.varDesc = varDesc;
    }

    public String getDataFormual() {
        return dataFormual;
    }

    public void setDataFormual(String dataFormual) {
        this.dataFormual = dataFormual;
    }

    public String getDataReverseFormual() {
        return dataReverseFormual;
    }

    public void setDataReverseFormual(String dataReverseFormual) {
        this.dataReverseFormual = dataReverseFormual;
    }
}
