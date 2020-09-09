package com.qdu.diaisheng.entity;
/**
 * @Author changliang
 * @Description user的实体类，用户类，对应数据库中user表
 * @Date 2019/7/20 11:55
 **/
public class User {
    private Integer userId;//用户id，主键自动生成
    private String userName;//用户名字
    private String phoneNum;//用户联系方式
    private String password;//用户登录密码
    private String userStatus;//用户状态，‘0’表示普通用户，‘1’表示管理员用户
    private String deviceId;//设备id

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
