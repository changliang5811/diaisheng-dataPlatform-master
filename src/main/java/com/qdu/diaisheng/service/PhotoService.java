package com.qdu.diaisheng.service;

import com.qdu.diaisheng.entity.Camera;
import com.qdu.diaisheng.entity.Photo;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    public int addPhoto(Photo photo);
    public Photo getNewPhoto(String cameraId);
    public Camera getCameraBydeviceId(String deviceId);
    public List<Photo> getHistoryPhotos(String stime,String etime,String cameraId);
}
