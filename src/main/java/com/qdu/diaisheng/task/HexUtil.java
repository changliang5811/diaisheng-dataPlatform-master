package com.qdu.diaisheng.task;

public class HexUtil {
    /**
     * 字符串转换成为16进制字符串（大写）
     * @explain 因为java转义字符串在java中有着特殊的意义，
     *     所以当字符串中包含转义字符串，并将其转换成16进制后，16进制再转成String时，会出问题：
     *  java会将其当做转义字符串所代表的含义解析出来
     * @param str 字符串（去除java转义字符）
     * @return 16进制字符串
     * @throws Exception
     */
    public static String toHexString(String str) throws Exception {
        // 用于接收转换结果
        String hexString = "";
        // 1.校验是否包含特殊字符内容
        // java特殊转义符
        // String[] escapeArray = {"\b","\t","\n","\f","\r","\'","\"","\\"};
        String[] escapeArray = {"\b","\t","\n","\f","\r"};
        // 用于校验参数是否包含特殊转义符
        boolean flag = false;
        // 迭代
        for (String esacapeStr : escapeArray) {
            // 一真则真
            if (str.contains(esacapeStr)) {
                flag = true;
                break;// 终止循环
            }
        }
        // 包含特殊字符
        if (flag) throw new Exception("参数字符串不能包含转义字符！");
        // 16进制字符
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        // String--&gt;byte[]
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i]&0x0f0) >> 4;
            sb.append(hexArray[bit]);
            bit = bs[i] & 0x0f;
            sb.append(hexArray[bit]);
        }
        hexString = sb.toString();
        return hexString;
    }
    /**
     * 将字符串转为16进制
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }
    private static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
    /**16进制字符串转String
     * @param: [hex] 十六进制字符串
     * @return: java.lang.String String串
     * @author: zhuoli
     * @date: 2018/6/4 12:48
     */
    public static String fromHex(String hex) {
        /*兼容带有\x的十六进制串*/
        hex = hex.replace("\\x","");
        char[] data = hex.toCharArray();
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("字符个数应该为偶数");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return new String(out);
    }
    /**
     * 字符串转换为16进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    /**
     *  byte字节数组转16进制
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    /**
     * 16进制转byte数组
     */
    public static byte[] hexStringToByteArray(String s){
        int len = s.length();
        byte[] data = new byte[len / 2];
        try {
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
            }
        } catch (Exception e) {
        }
        return data;
    }
    /**
     * 字符串转换成ASCII码
     * @param value
     * @return
     */
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]);
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     *  将ASCII转成字符串
     * @param value
     * @return
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * ASCII转换成16进制
     * @param str
     * @return
     */
    public static String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    /**
     * 十六进制转换成ASCII码字符串
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }
}
