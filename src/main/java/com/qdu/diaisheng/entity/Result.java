package com.qdu.diaisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 返回的结果集
 * @author 周长亮
 *
 */
public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg;//输出信息
	private String errorMsg;//输出错误信息
	private Object object=null;//返回的类
	private boolean success;//判断是否成功
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
