package com.qdu.diaisheng.entity;

public class Photo {
    private Integer photoId;
    private String createTime;
    private String content;
    private String cameraId;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }
}
