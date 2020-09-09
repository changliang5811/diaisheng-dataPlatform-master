/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qdu.diaisheng.task;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wangxi
 */
public class ClientHander {
        Socket clientSocket;
        ServerSocket serverSocket;
        DBMysqlUtil DBUtil =null;
        private   String DBDRIVER = "com.mysql.jdbc.Driver";
        private   String DBURL = "jdbc:mysql://106.12.184.95:3306/diaisheng?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
        private   String DBUSER = "root";
        private   String DBPASSWORD = "diaisheng";
        OutputStream outputStream = null;
        InputStream inputStream = null;
        private Logger logger = Logger.getLogger(this.getClass());

        public ClientHander(Socket clientSocket, ServerSocket serverSocket){
            this.clientSocket=clientSocket;
            this.serverSocket = serverSocket;
        }
      
        public void init(){
            DBUtil=new DBMysqlUtil(DBDRIVER,DBURL,DBUSER,DBPASSWORD);

        }
        
        public  void readData() {
            //byte[] toDTU2=new byte[]{0x02,0x06,0x00,0x13,0x00,(byte)0xFF,(byte)0x38,(byte)0x7C};//开启COD
            //byte[] data11=new byte[]{0x02,0x06,0x00,0x13,0x00,0x0F,(byte)0x38 ,(byte)0x38}; //关掉远程启动
            String[] s1 = null;
            String ss="";
            byte[] bytes = null;
            StringBuilder sb = null;
            int count = 0;
            String decData="";
            Connection conn = null;
            try {
                init();
                conn = DBUtil.getConnection();
                byte[] data11=new byte[]{0x01,0x03,0x00,0x02,0x00,0x01,(byte)0x25 ,(byte)0xCA}; //出水温度32442
                s1 = queryData(data11);
                if (s1!=null){
                    ss = new BigInteger(s1[3]+s1[4],16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss)/100); //String.format("%.2f",);
                   // System.out.println("\n获取出水温度为："+ss+"------->"+Float.parseFloat(decData)+"\n");
                    insertData(conn,"32442",decData);
                }
                byte[] data12=new byte[]{0x01,0x03,0x00,0x07,0x00,0x01,(byte)0x35 ,(byte)0xCB}; //出水累计流量小数30948
                s1 = queryData(data12);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                    //System.out.println("\n获取出水累计流量小数为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"30948", decData);
                }
                byte[] data13=new byte[]{0x01,0x03,0x00,0x05,0x00,0x02,(byte)0xD4 ,(byte)0x0A}; //出水累计流量整数30946
                s1 = queryData(data13);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4] + s1[5] + s1[6], 16).toString();//转换成十进制
                    decData = String.valueOf(Integer.parseInt(ss));
                    //System.out.println("\n获取出水累计流量整数为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"30946", decData);
                }
                byte[] data14=new byte[]{0x01,0x03,0x00,0x00,0x00,0x02,(byte)0xC4 ,(byte)0x0B}; //出水瞬时流量30945
                s1 = queryData(data14);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4] + s1[5] + s1[6], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                    //System.out.println("\n获取出水瞬时流量为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"30945", decData);
                }
                byte[] data21=new byte[]{0x02,0x03,0x00,0x13,0x00,0x01,(byte)0x75 ,(byte)0xFC}; //远程启动35290
                s1 = queryData(data21);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Integer.parseInt(ss));
                   // System.out.println("\n获取远程启动为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"35290", decData);
                }
                byte[] data22=new byte[]{0x02,0x03,0x00,0x00,0x00,0x02,(byte)0xC4,(byte)0x38};//COD即时数据30947
                s1 = queryData(data22);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4] + s1[5] + s1[6], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                   // System.out.println("\n获取COD即时数据为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"30947", decData);
                }
               byte[] data31=new byte[]{0x03,0x03,0x00,0x00,0x00,0x01,(byte)0x85,(byte)0xE8};//进水瞬时流量32445
                s1 = queryData(data31);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                   // System.out.println("\n获取进水瞬时流量为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32445", decData);
                }
                byte[] data32=new byte[]{0x03,0x03,0x00,0x02,0x00,0x01,(byte)0x24,(byte)0x28};//进水温度32443
                s1 = queryData(data32);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 100);
                   // System.out.println("\n获取进水温度为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32443", decData);
                }
                byte[] data33=new byte[]{0x03,0x03,0x00,0x0A,0x00,0x01,(byte)0xA5,(byte)0xEA};//进水正累积流量小数32279
                s1 = queryData(data33);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                    //System.out.println("\n获取进水正累积流量小数为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32279", decData);
                }
                byte[] data34=new byte[]{0x03,0x03,0x00,0x08,0x00,0x02,(byte)0x44,(byte)0x2B};//进水正累积流量整数32278
                s1 = queryData(data34);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4] + s1[5] + s1[6], 16).toString();//转换成十进制
                    decData = String.valueOf(Integer.parseInt(ss));
                   // System.out.println("\n获取进水正累积流量整数为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32278", decData);
                }
                byte[] data41=new byte[]{0x04,0x03,0x01,0x03,0x00,0x01,(byte)0x75,(byte)0xA3};//罐壁温度32270
                s1 = queryData(data41);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 10);
                    //System.out.println("\n获取罐壁温度为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32270", decData);
                }
                byte[] data42=new byte[]{0x04,0x03,0x01,0x02,0x00,0x01,(byte)0x24,(byte)0x63};//罐内温度32269
                s1 = queryData(data42);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 10);
                   // System.out.println("\n获取罐内温度为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32269", decData);
                }
                byte[] data43=new byte[]{0x04,0x03,0x01,0x00,0x00,0x01,(byte)0x85,(byte)0xA3};//设定温度32268
                s1 = queryData(data43);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 10);
                   // System.out.println("\n获取设定温度为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"32268", decData);
                }
                /*byte[] data51=new byte[]{0x05,0x03,0x00,0x02,0x00,0x01,(byte)0x24,(byte)0x4E};//高浓排进水温度41610
                s1 = queryData(data51);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 100);
                   // System.out.println("\n获取高浓排进水温度为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"41610", decData);
                }
                byte[] data52=new byte[]{0x05,0x03,0x00,0x00,0x00,0x01,(byte)0x85,(byte)0x8E};//高浓排瞬时流量41609
                s1 = queryData(data52);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                   // System.out.println("\n获取高浓排瞬时流量为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"41609", decData);
                }
                byte[] data53=new byte[]{0x05,0x03,0x00,0x0A,0x00,0x01,(byte)0xA5,(byte)0x8C};//高浓排正累计流量小数41608
                s1 = queryData(data53);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4], 16).toString();//转换成十进制
                    decData = String.valueOf(Float.parseFloat(ss) / 1000);
                   // System.out.println("\n获取高浓排正累计流量小数为：" + ss + "------->" + decData + "\n");
                    insertData(conn,"41608", decData);
                }
                byte[] data54=new byte[]{0x05,0x03,0x00,0x08,0x00,0x02,(byte)0x44,(byte)0x4D};//高浓排正累计流量整数41607
                s1 = queryData(data54);
                if (s1!=null) {
                    ss = new BigInteger(s1[3] + s1[4] + s1[5] + s1[6], 16).toString();//转换成十进制
                    decData = String.valueOf(Integer.parseInt(ss));
                    //System.out.println("\n获取高浓排正累计流量整数为：" + ss + "------->" + decData);
                    insertData(conn,"41607", decData);
                }*/
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    inputStream.close();
                    outputStream.close();
                    clientSocket.close();
                    conn.close();
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public void insertData(Connection conn,String dataPointId,String data){
            //插入数据库的代码
            String sql="insert into data_value(data_point_id,create_time,val) values(?,?,?)";
            try{
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,dataPointId);
                SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ps.setString(2,f.format(new Date()));
                ps.setFloat(3,Float.valueOf(data));
                int effected=ps.executeUpdate();
                if(effected>0){
                   logger.info("传感器数据插入成功");
                }else{
                    logger.info("传感器数据插入失败");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public String[] queryData(byte[] data) throws Exception{
                String[] returnDtuData = null;
                StringBuilder sb = new StringBuilder();
                outputStream = clientSocket.getOutputStream();
                outputStream.write(data);//发送给Modbus Slave软件的消息
                inputStream = clientSocket.getInputStream();
                // 读取DTU发来的数据
                // 接下来就是获取套接字里的字节流，并转化为字符串
                byte[] bytes = new byte[1024];
                int count = inputStream.read(bytes);//获取返回数据第一行，因为其余行会返回心跳包数据
                for(int i=0;i<count;i++){
                    sb.append(Integer.toHexString(bytes[i])+" ");//把返回数据转换为16进制
                }
               // System.out.println("原始数据："+sb.toString());
                if(!sb.substring(0,2).equals("77")){
                    returnDtuData = sb.toString().split(" ");
                    for (int i =0;i<returnDtuData.length;i++){
                        if (returnDtuData[i].charAt(0)=='f'){
                            if (returnDtuData[i].length()>2){
                                returnDtuData[i] = returnDtuData[i].substring(6);
                            }
                        }
                        if (returnDtuData[i].length()==1)
                            returnDtuData[i] = "0"+returnDtuData[i];
                    }
                    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   // System.out.print("\n获取时间为："+f.format(new Date()));
                }

                return returnDtuData;
        }
       
        
        
}
