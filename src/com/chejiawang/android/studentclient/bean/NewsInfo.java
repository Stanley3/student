package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
import java.util.Date;

public class NewsInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer news_info_id;
	private Integer sender;
	private Date send_time;
	private String content;
	private Integer receiver_type;
	private Integer attribute;
	private Integer validation;
	public void setNews_info_id(Integer news_info_id){
		this.news_info_id = news_info_id;
	}
	public Integer getNews_info_id() {
		return news_info_id;
	}
	public void setSender(Integer sender){
		this.sender = sender;
	}
	public Integer getSender() {
		return sender;
	}
	public void setSend_time(Date send_time){
		this.send_time = send_time;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setReceiver_type(Integer receiver_type){
		this.receiver_type = receiver_type;
	}
	public Integer getReceiver_type() {
		return receiver_type;
	}
	public void setAttribute(Integer attribute){
		this.attribute = attribute;
	}
	public Integer getAttribute() {
		return attribute;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
}
