package com.qdu.diaisheng.task;

import com.alibaba.fastjson.JSONObject;
import com.qdu.diaisheng.entity.Photo;
import com.qdu.diaisheng.service.PhotoService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Component
//@EnableScheduling
//@EnableAsync
public class TimerTask {
   /* public static int PORT=20001;
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private PhotoService photoService;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    public static ServerSocket createSocket() throws IOException {
        ServerSocket socketServer=new ServerSocket();
        return socketServer;
    }
    public void initRequestConfig(){

    }
    public static void initSocket(ServerSocket serverSocket) throws SocketException {
        serverSocket.setReuseAddress(true);
        serverSocket.setReceiveBufferSize(6*1024*1024);
        serverSocket.setPerformancePreferences(2,2,1);
    }*/
   // @Async
   // @Scheduled(cron = "0 */5 * * * ?")
    /*public void queryDatasFromDtu(){
        try{
            serverSocket=new ServerSocket();//给云服务器制定端口，初始化
            //logger.info("监听端口成功...");
            initSocket(serverSocket);//连接属性初始化设置
            serverSocket.bind(new InetSocketAddress(Inet4Address.getLocalHost(),PORT),50);
            logger.info("start server successful......"+Inet4Address.getLocalHost().toString());
            clientSocket=serverSocket.accept();// 如果有请求到达，则接受请求，并建立一个新的套接字
            logger.info("accept tcp client "+clientSocket.getRemoteSocketAddress().toString());
            //logger.info("本地ip为:"+clientSocket.getLocalAddress().toString()+"远程ip地址为："+clientSocket.getRemoteSocketAddress().toString());
            ClientHander hander=new ClientHander(clientSocket,serverSocket);
            hander.readData();
        }catch (Exception e){
            logger.error("spring定时任务出错！",e);
        }
    }*/
    /**
     * @Author wangxi
     * @Description 定时执行任务，每半小时从接口获取图片的Base64编码并存入数据库中
     * @Date 2019/7/21 23:35
     * @Param [request, response]
     * @return
     **/
    //@Async
    //@Scheduled(cron = "0 */30 * * * ?")
    /*public void timerAddPhoto(){
        String url="http://39.108.213.89:10100/GetSnapshotPic";
        Map<String,Object> map=new HashMap<>();
        CloseableHttpClient client=null;
        HttpGet httpGet=null;
        HttpResponse response=null;
        try{
            String charset="UTF-8";
            client= HttpClientBuilder.create().build();
            httpGet=new HttpGet(url);
            //HttpClient中可设置三个超时：RequestTimeout（连接池获取到连接的超时时间）、ConnectTimeout（建立连接的超时）、SocketTimeout（获取数据的超时时间）。
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(100000).setConnectTimeout(100000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-Type","application/x-www-form-urlencoded");
            httpGet.addHeader("charset",charset);
            httpGet.setURI(URI.create(url + "?deviceid=c844150007b9&serverip=39.108.213.89"));
            response=client.execute(httpGet);
            String res= EntityUtils.toString(response.getEntity());
            JSONObject jsonObject=JSONObject.parseObject(res);
            String Base64=jsonObject.getString("picBase64");
            if(Base64!=null){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date current=new Date();
                String formatCurrent=sdf.format(current);
                Photo p=new Photo();
                p.setCameraId("c844150007b9");
                p.setCreateTime(formatCurrent);
                p.setContent(Base64);
                if(photoService.addPhoto(p)>0){
                    logger.info("摄像头图片添加成功");
                }else{
                    logger.warn("摄像头图片添加失败");
                }
            }else{
                logger.warn("摄像头图片为空！");
            }
        }catch (Exception e){
            map.put("msg","java获取设备实时抓拍图片接口异常");
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
            try{
                if(client!=null){
                    client.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/


}
