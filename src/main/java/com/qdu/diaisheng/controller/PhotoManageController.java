package com.qdu.diaisheng.controller;

import com.alibaba.fastjson.JSONObject;
import com.qdu.diaisheng.entity.Camera;
import com.qdu.diaisheng.entity.Photo;
import com.qdu.diaisheng.entity.Result;
import com.qdu.diaisheng.service.PhotoService;
import com.qdu.diaisheng.task.CompratorByLastModified;
import com.qdu.diaisheng.util.Md5;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName PhotoManageController
 * @Author changliang
 * @description 定时拍照相关接口调用demo，仅供参考用。
 * @Date 2019/8/9 14:53
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/photos")
public class PhotoManageController {
    @Autowired
    private PhotoService photoService;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * @Author changliang
     * @Description 登录拍照设备，获取账户控制令牌（access_token），有效期24小时
     * @Date 2019/8/9 14:55
     * PostMethod
     **/
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login() {
        String url = "http://user.hddvs.net:8080/apiv2/LoginAction.php";
        Map<String, Object> map = new HashMap<>();
        HttpClient client = null;
        PostMethod method = null;
        try {
            String charset = "UTF-8";
            client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接时间
            method = new PostMethod(url);//post请求
            method.getParams().setContentCharset(charset);//设置传入参数编码为UTF-8
            method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//设置php头文件设置，不然会出现非法参数异常
            method.setRequestHeader("charset", "UTF-8");
            method.setParameter("username", "diaisheng");
            method.setParameter("password", Md5.md5("diaisheng"));
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//随机生成uuid
            method.setParameter("state", uuid);
            int code = client.executeMethod(method);
            map.put("msg", "java访问php登陆接口成功");
            map.put("code", code);//200表示成功，其余表示失败
            map.put("data", method.getResponseBodyAsString());//获取封装的json数据，以便下一步操作
            System.out.println(map.get("data"));
            logger.info("java访问php登陆接口成功");
            return map;
        } catch (Exception e) {
            map.put("msg", "java访问php登陆接口异常");
            logger.error("java访问php登陆接口异常", e);
        } finally {
            method.releaseConnection();
            client = null;
        }
        return map;
    }

    /**
     * @Author changliang
     * @Description 登录拍照设备，获取账户控制令牌（access_token），有效期24小时
     * @Date 2019/8/9 14:55
     * HttpPost
     **/
    @RequestMapping(value = "/login2")
    @ResponseBody
    public Map<String, Object> loginDemo() {
        String url = "http://user.hddvs.net:8080/apiv2/LoginAction.php";
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        HttpResponse response = null;
        try {
            String charset = "UTF-8";
            client = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            httpPost.setConfig(requestConfig);//设置post状态参数
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("charset", "UTF-8");
            List<BasicNameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("username", "diaisheng"));
            list.add(new BasicNameValuePair("password", Md5.md5("diaisheng")));
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//随机生成uuid
            System.out.println(uuid);
            list.add(new BasicNameValuePair("state", uuid));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            //response响应
            response = client.execute(httpPost);
            String res = EntityUtils.toString(response.getEntity());
            map.put("data", res);//获取封装的json数据，以便下一步操作
            logger.info("java访问php登陆接口成功");
            return map;
        } catch (Exception e) {
            map.put("msg", "java访问php登陆接口异常");
            logger.error("java访问php登陆接口异常", e);
        } finally {
            httpPost.releaseConnection();
            try {
                if (client != null)
                    client.close();
            } catch (IOException e) {
                logger.error("client关闭出现异常(登录拍照设备)", e);
            }
        }
        return map;
    }

    /**
     * @Author wangxi
     * @Description 获取组设备列表
     * @Date 2019/8/11 12:55
     * HttpPost
     **/

    @RequestMapping(value = "/getDeviceList")
    @ResponseBody
    public Map<String, Object> getDeviceList(String accessToken, String token) {

        String url = "http://user.hddvs.net:8080/apiv2/GetGroupDeviceAction.php";
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient client = null;
        HttpPost post = null;
        HttpResponse response = null;
        try {
            String charset = "UTF-8";
            client = HttpClientBuilder.create().build();
            post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            post.setConfig(requestConfig);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.addHeader("charset", charset);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", "diaisheng"));
            params.add(new BasicNameValuePair("access_token", accessToken));
            params.add(new BasicNameValuePair("state", token));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, charset);
            post.setEntity(urlEncodedFormEntity);
            response = client.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            map.put("data", res);
        } catch (Exception e) {
            map.put("msg", "java获取组设备列表接口异常");
            logger.error("java获取组设备列表接口异常", e);
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                logger.error("client关闭出现异常(获取组设备列表)", e);
            }
        }
        return map;
    }

    /**
     * @Author wangxi
     * @Description 保存设备信息（可在设备中调）
     * @Date 2019/8/11 16:55
     * HttpPost
     **/
    @RequestMapping(value = "/saveDevice")
    @ResponseBody
    public Map<String, Object> saveDevice() {
        String url = "http://39.108.213.89:10100/SaveDevInfo";
        CloseableHttpClient client = null;
        Map<String, Object> map = new HashMap<>();
        HttpPost post = null;
        HttpResponse response = null;
        String charset = "UTF-8";
        try {
            post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
            post.setConfig(requestConfig);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.addHeader("charset", charset);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("deviceid", ""));//设备id
            params.add(new BasicNameValuePair("serverip", ""));//服务器地址
            params.add(new BasicNameValuePair("devicename", ""));//设备名称
            params.add(new BasicNameValuePair("Snaptime1", ""));//抓拍时间1", 定时器1，以秒为单位，值为：hour*3600+min*60+sec
            params.add(new BasicNameValuePair("Snaptime2", ""));//抓拍时间2",定时器2，同上
            params.add(new BasicNameValuePair("SnapInterval", ""));//间隔抓拍",以10秒为单位，每隔n*10秒整点抓拍一张
            params.add(new BasicNameValuePair("FtpAddr", ""));//FTP服务器地址
            params.add(new BasicNameValuePair("FtpUserName", ""));//ftp服务器用户名（test）
            params.add(new BasicNameValuePair("FtpPassword", ""));//FTP服务器用户名密码（123456）
            params.add(new BasicNameValuePair("FtpPort", ""));//存放路径，参数为”/”或者空””的话，会自动创建目录/id/yyyymmdd/到ftp服务器下，
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, charset);
            post.setEntity(urlEncodedFormEntity);
            client.execute(post);
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "java保存设备信息接口异常");
            map.put("success", false);
            logger.error("java访保存设备信息接口异常", e);
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                logger.error("client关闭出现异常(保存设备信息)", e);
            }
        }
        return map;
    }

    @RequestMapping(value = "/getnewphoto")
    @ResponseBody
    public Map<String, Object> getNewPhoto(String deviceId) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (deviceId==null||"".equals(deviceId)){
            map.put("err", 1);
            map.put("errMag", "未查到图片");
            return map;
        }
        //String camerId = "c844150007b9";
       /* Camera camera = photoService.getCameraBydeviceId(deviceId);
        if (camera == null) {
            map.put("err", 1);
            map.put("errMag", "未查到图片");
            return map;
        }*/
        //Photo p = photoService.getNewPhoto(camera.getCameraId());
        Photo p = photoService.getNewPhoto("diaisheng");//此处先写死
        if (p == null) {
            map.put("err", 1);
            map.put("errMag", "未查到图片");
        } else {
            //map.put("picBase64", p.getContent());
            map.put("photoPath", p.getPath());
            map.put("err", 0);
        }
        return map;
    }

    @RequestMapping(value = "/getnewphototest")
    @ResponseBody
    public String getNewPhotoTest() {
        String url = "http://39.108.213.89:10100/GetSnapshotPic";
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        try {
            String charset = "UTF-8";
            client = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("charset", charset);
            httpGet.setURI(URI.create(url + "?deviceid=c844150007b9&serverip=39.108.213.89"));
            response = client.execute(httpGet);
            String res = EntityUtils.toString(response.getEntity());
            return res;
        } catch (Exception e) {
            map.put("msg", "java获取设备实时抓拍图片接口异常");
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "err:1";

    }

    /**
     * 往数据库插入最新一张图片
     * @return
     */
    @RequestMapping(value = "/insertCurrentPhoto")
    @ResponseBody
    public Map getcurrentPhoto(String deviceId) {
        String url = "http://39.108.213.89:10100/GetSnapshotPic";
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        try {
            if (deviceId==null||"".equals(deviceId)){
                map.put("success",false);
                map.put("message","设备点异常！！");
                return map;
            }
            Camera camera= photoService.getCameraBydeviceId(deviceId);
            if (camera==null){
                map.put("success",false);
                map.put("message","设备点异常！！");
                return map;
            }
            String charset = "UTF-8";
            client = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("charset", charset);
            httpGet.setURI(URI.create(url + "?deviceid="+camera.getCameraId()+"&serverip="+camera.getServerIp()));
            response = client.execute(httpGet);
            String res = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(res);
            String Base64 = jsonObject.getString("picBase64");
            if (Base64 != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date current = new Date();
                String formatCurrent = sdf.format(current);
                Photo p = new Photo();
               // p.setCameraId("c844150007b9");
                p.setCameraId(camera.getCameraId());
                p.setCreateTime(formatCurrent);
                p.setContent(Base64);
                if (photoService.addPhoto(p) > 0) {
                    logger.info("摄像头图片添加成功");
                    map.put("success",true);
                    map.put("message","插入成功！");
                } else {
                    logger.warn("摄像头图片添加失败");
                    map.put("success",false);
                    map.put("message","插入失败！");
                }
            }else{
                logger.warn("摄像头图片为空！");
                map.put("success",false);
                map.put("message","抓拍图片接口异常！！");
            }
        } catch (Exception e) {
            map.put("msg", "java获取设备实时抓拍图片接口异常");
            map.put("success",false);
            map.put("message","数据库异常！");
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    /**
     * 历史图片页面
     * @author changliang
     */
    @RequestMapping(value = "/historyPhotos")
    public String historyPhotos(){
        return "admin/historyPhotos";
    }
    /**
     * 图片历史数据滚动查询
     * @author changliang
     */
    @RequestMapping(value = "/selectHistoryPhotos",method = RequestMethod.GET)
    @ResponseBody
    public Result selectPhotos(String stime,String etime,String deviceId){
        Result result = new Result();
       try{
           if (deviceId==null||"".equals(deviceId)){
               result.setErrorMsg("查询失败，设备点异常");
               result.setSuccess(false);
           }
           Camera camera= photoService.getCameraBydeviceId(deviceId);
           if (camera==null){
               result.setErrorMsg("查询失败，设备点异常");
               result.setSuccess(false);
               return result;
           }
           if (stime!=null&&etime!=null){
               result.setSuccess(true);
               List<Photo> listPhoto = photoService.getHistoryPhotos(stime,etime,camera.getCameraId());
               result.setObject(listPhoto);
               result.setMsg("恭喜您，查询成功！");
           }else{
               result.setErrorMsg("查询失败，请选择正确时间");
               result.setSuccess(false);
           }
       }catch (Exception e){
           logger.error("查询历史图片异常！");
           result.setErrorMsg("查询失败，数据库异常");
           result.setSuccess(false);
       }
        return result;
    }



}
