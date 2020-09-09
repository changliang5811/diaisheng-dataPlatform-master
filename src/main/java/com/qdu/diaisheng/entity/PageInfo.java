package com.qdu.diaisheng.entity;
/**
 * layui数据表格pojo类
 * @author 周长亮
 *
 */
public class PageInfo {
	private int code=0;
	private String msg;
	private int count;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
