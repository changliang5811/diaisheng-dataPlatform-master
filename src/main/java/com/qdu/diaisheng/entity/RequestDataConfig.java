package com.qdu.diaisheng.entity;

public class RequestDataConfig {
    private int SlavaId;
    private int[] Message;
    private short Length;
    private String[] Column;
    private String[] Type;


    public int getSlavaId() {
        return SlavaId;
    }

    public void setSlavaId(int slavaId) {
        SlavaId = slavaId;
    }

    public int[] getMessage() {
        return Message;
    }

    public void setMessage(int[] message) {
        Message = message;
    }

    public short getLength() {
        return Length;
    }

    public void setLength(short length) {
        Length = length;
    }

    public String[] getColumn() {
        return Column;
    }

    public void setColumn(String[] column) {
        Column = column;
    }

    public String[] getType() {
        return Type;
    }

    public void setType(String[] type) {
        Type = type;
    }
}
