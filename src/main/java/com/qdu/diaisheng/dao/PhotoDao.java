package com.qdu.diaisheng.dao;

import com.qdu.diaisheng.entity.Camera;
import com.qdu.diaisheng.entity.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoDao {
    public int addPhoto(Photo photo);
    public Photo getNewPhotoByCamera(@Param("cameraId")String cameraId);
    public Camera getCameraIdBydeviceId(@Param("deviceId")String deviceId);
    public List<Photo> getHistoryPhotos(@Param("stime")String stime,@Param("etime")String etime,@Param("cameraId")String cameraId);
}
