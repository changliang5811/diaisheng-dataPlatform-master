package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.Device;
import java.util.List;

public interface DeviceDao {
    int insertDevice(Device device);
    List<Device> queryDevice(int userId);//通过userid查询设备
    List<Device> getAllDevice();
    List<Device> getDeviceByName(String userName);
}
