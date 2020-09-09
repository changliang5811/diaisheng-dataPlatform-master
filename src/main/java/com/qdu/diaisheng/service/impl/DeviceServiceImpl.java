package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.dao.DeviceDao;
import com.qdu.diaisheng.entity.Device;
import com.qdu.diaisheng.entity.User;
import com.qdu.diaisheng.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceDao deviceDao;

    @Override
    public List<Device> getDeviceList(User user) {
        if(user.getUserStatus().equals("1")){
            return deviceDao.getAllDevice();
        }else{
            return deviceDao.queryDevice(user.getUserId());
        }

    }

    @Override
    public List<Device> getDeviceListByName(String userName) {

        if(userName.equals("admin")){
            return deviceDao.getAllDevice();
        }else{
            return deviceDao.getDeviceByName(userName);
        }

    }
}
