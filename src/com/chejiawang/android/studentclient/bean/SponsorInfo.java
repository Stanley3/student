package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;

public class SponsorInfo implements Serializable{
	private Integer sponsor_id;
	private String sponsor_name;
	private String amount;
	private String time;
	private String advertisement;
	private String ratio;
	private Integer validation;
	public void setSponsor_id(Integer sponsor_id){
		this.sponsor_id = sponsor_id;
	}
	public Integer getSponsor_id() {
		return sponsor_id;
	}
	public void setSponsor_name(String sponsor_name){
		this.sponsor_name = sponsor_name;
	}
	public String getSponsor_name() {
		return sponsor_name;
	}
	public void setAmount(String amount){
		this.amount = amount;
	}
	public String getAmount() {
		return amount;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setAdvertisement(String advertisement){
		this.advertisement = advertisement;
	}
	public String getAdvertisement() {
		return advertisement;
	}
	public void setRatio(String ratio){
		this.ratio = ratio;
	}
	public String getRatio() {
		return ratio;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
}
