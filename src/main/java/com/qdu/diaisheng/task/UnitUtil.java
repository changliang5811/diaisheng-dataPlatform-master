package com.qdu.diaisheng.task;
public class UnitUtil {
    public static short getUint8(short s){
        return (short) (s & 0x00ff);
    }

    public static int getUint16(int i){
        return i & 0x0000ffff;
    }

    public static long getUint32(long l){
        return l & 0x00000000ffffffff;
    }

}
