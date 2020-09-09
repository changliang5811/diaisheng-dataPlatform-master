package com.qdu.diaisheng.service.impl;

import com.qdu.diaisheng.dao.PhotoDao;
import com.qdu.diaisheng.entity.Camera;
import com.qdu.diaisheng.entity.Photo;
import com.qdu.diaisheng.service.PhotoService;
import com.qdu.diaisheng.task.CompratorByLastModified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Component
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoDao photoDao;

    @Override
    public int addPhoto(Photo photo) {
        if(photo==null) {
            return -1;
        }else {
            int row=photoDao.addPhoto(photo);
            if(row<=0)
                return -1;
        }
        return 1;
    }

    @Override
    public Photo getNewPhoto(String cameraId){
        if (cameraId == null)
            return null;
        else {
            Photo photo = new Photo();
            //return photoDao.getNewPhotoByCamera(cameraId);
            try {
                // 定义list，用于存储数据文件的全路径
                List<String> list =null;
                String dataFileTempDir = "";
                Integer i= 0;
                while (list == null){//循环遍历，直到文件夹存在
                    dataFileTempDir = "/home/yukun/"+getPhotoDate(i)+"/images";
                    // 得到返回文件全路径的list集合
                    list = getFiles(dataFileTempDir);
                    i++;
                }
                //将图片路径存起来返回到前台
                photo.setPath(list.get(0).substring(12));//格式如   20200711/images/P20071114150110.jpg


            } catch (Exception e) {
                e.printStackTrace();
            }
            return photo;
        }
    }

    @Override
    public Camera getCameraBydeviceId(String deviceId) {
        if (deviceId==null||deviceId.equals("")){
            return null;
        }
        return photoDao.getCameraIdBydeviceId(deviceId);
    }

    /**
     * 查询历史图片数据
     * @param stime
     * @param etime
     * @return list
     */
    @Override
    public List<Photo> getHistoryPhotos(String stime, String etime,String cameraId) {
        if (stime==null||etime==null||cameraId==null)
            return null;
        else{
            return photoDao.getHistoryPhotos(stime,etime,cameraId);
        }
    }

    /**
     * 通过递归得到某一路径下所有的文件的全路径,分装到list里面
     *
     * @param filePath
     * @return
     */
    public static List<String> getFiles(String filePath) {
        List<String> filelist = new ArrayList<String>();
        File root = new File(filePath);
        if (!root.exists()) {
            //System.out.println(filePath + " not exist!");
            return null;
        } else {
            File[] files = root.listFiles();
            Arrays.sort(files, new CompratorByLastModified());
            for (File file : files) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath());
                } else {
                    //logger.info("目录:" + filePath + "文件全路径:" + file.getAbsolutePath());
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
        return filelist;
    }
    public static String getPhotoDate(Integer n){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //获取当前时间24小时*n天前的时间
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY,c.get(Calendar.HOUR_OF_DAY) - 24*n);
        Date time= c.getTime();
        String s = simpleDateFormat.format(time);
        return s;
    }

}
