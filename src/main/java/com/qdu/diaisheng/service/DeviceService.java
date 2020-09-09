package com.qdu.diaisheng.service;
import com.qdu.diaisheng.entity.Device;
import com.qdu.diaisheng.entity.User;

import java.util.List;

public interface DeviceService {
    List<Device> getDeviceList(User user);

    List<Device> getDeviceListByName(String userName);


}
