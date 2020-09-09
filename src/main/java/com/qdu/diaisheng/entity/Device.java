package com.qdu.diaisheng.entity;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;

public class Device {
    private String deviceId;//设备ID
    private String deviceName;//设备名
    private String createTime;//设备创建时间
    private String lastTime;//最近一次的修改时间
    private Integer userId;
    private Integer deviceStatus;//设备状态 1：在线 0：不在线
    private String lon;
    private String lat;

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
