package com.qdu.diaisheng;

public enum DataValueEnum {



    SUCCESS(1, "成功"), INNER_ERROR(-1001, "操作失败"),EMPTY(404,"数据为空"),PAR_EMPTY(-1002,"参数为空");


    private int state;

    private String stateInfo;

    private DataValueEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static DataValueEnum stateOf(int index) {
        for (DataValueEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }





}
