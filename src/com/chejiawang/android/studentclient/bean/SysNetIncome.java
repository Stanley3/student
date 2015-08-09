package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
import java.util.Date;
public class SysNetIncome implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer net_income_id;
	private Integer net_income_source;
	private String amount;
	private Date time;
	private Integer validation;
	public void setNet_income_id(Integer net_income_id){
		this.net_income_id = net_income_id;
	}
	public Integer getNet_income_id() {
		return net_income_id;
	}
	public void setNet_income_source(Integer net_income_source){
		this.net_income_source = net_income_source;
	}
	public Integer getNet_income_source() {
		return net_income_source;
	}
	public void setAmount(String amount){
		this.amount = amount;
	}
	public String getAmount() {
		return amount;
	}
	public void setTime(Date time){
		this.time = time;
	}
	public Date getTime() {
		return time;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
}
