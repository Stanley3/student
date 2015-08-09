package com.chejiawang.android.studentclient.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @author myfu
 * web 全部订单 界面bean文件
 */
public class OrderInfo{
	private Integer order_id;
	private Integer student_id;
	private Integer coach_id;
	private String order_time;
	private String order_amount;
	private Integer order_status;
	private String training_start_time;
	private String training_end_time;
	private Integer validation;
	private Integer course_status;
	private Integer operator;
	private String coach_name;
	private String student_phone;
	private String coach_phone;
	private Integer Student_level;
	private String school_name;
	private String student_name;
	private String operation;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getCoach_phone() {
		return coach_phone;
	}
	public void setCoach_phone(String coach_phone) {
		this.coach_phone = coach_phone;
	}
	public Integer getStudent_level() {
		return Student_level;
	}
	public void setStudent_level(Integer student_level) {
		Student_level = student_level;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public String getTraining_start_time() throws ParseException {
		if(training_start_time != null && !training_start_time.isEmpty()){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			training_start_time = format.format(format.parse(training_start_time));
		}
		return training_start_time;
	}
	public void setTraining_start_time(String training_start_time) {
		this.training_start_time = training_start_time;
	}
	public String getTraining_end_time() throws ParseException {
		if(training_end_time != null && !training_end_time.isEmpty()){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			training_end_time = format.format(format.parse(training_end_time));
		}
		return training_end_time;
	}
	public void setTraining_end_time(String training_end_time) {
		this.training_end_time = training_end_time;
	}
	public Integer getValidation() {
		return validation;
	}
	public void setValidation(Integer validation) {
		this.validation = validation;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public String getCoach_name() {
		return coach_name;
	}
	public void setCoach_name(String coach_name) {
		this.coach_name = coach_name;
	}
	
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
}