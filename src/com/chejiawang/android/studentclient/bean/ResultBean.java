package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;

public class ResultBean implements Serializable{

	/**
	 * @author gujh
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String desc;
	
	public ResultBean(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}