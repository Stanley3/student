package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
import java.util.Date;

public class ScheduleInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer schedule_id;
	private Integer coach_id;
	private Integer operator_id;
	private Date operate_time;
	private Integer subject;
	private String content;
	private String student_toplimit;
	private String student_attribute;
	private String schedule_date;
	private Integer device_attribude;
	private Integer validation;
	public void setSchedule_id(Integer schedule_id){
		this.schedule_id = schedule_id;
	}
	public Integer getSchedule_id() {
		return schedule_id;
	}
	public void setCoach_id(Integer coach_id){
		this.coach_id = coach_id;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setOperator_id(Integer operator_id){
		this.operator_id = operator_id;
	}
	public Integer getOperator_id() {
		return operator_id;
	}
	public void setOperate_time(Date operate_time){
		this.operate_time = operate_time;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setSubject(Integer subject){
		this.subject = subject;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setStudent_toplimit(String student_toplimit){
		this.student_toplimit = student_toplimit;
	}
	public String getStudent_toplimit() {
		return student_toplimit;
	}
	public void setStudent_attribute(String student_attribute){
		this.student_attribute = student_attribute;
	}
	public String getStudent_attribute() {
		return student_attribute;
	}
	public void setSchedule_date(String schedule_date){
		this.schedule_date = schedule_date;
	}
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setDevice_attribude(Integer device_attribude){
		this.device_attribude = device_attribude;
	}
	public Integer getDevice_attribude() {
		return device_attribude;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
}
