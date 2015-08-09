package com.chejiawang.android.studentclient.bean;

public class MyOrderRecord {
	private Integer coach_id;
	private String coach_name;
	private String phone;
	private String school_name;
	private Integer course_status;
	private Integer teach_aging;
	private Integer device_type;
	private Integer star;
	private Integer service_count;
	private String training_start_time;
	private String training_end_time;
	private Integer order_status;
	private Integer order_id;
	private Integer student_id;
	
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public String getCoach_name() {
		return coach_name;
	}
	public void setCoach_name(String coach_name) {
		this.coach_name = coach_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public Integer getTeach_aging() {
		return teach_aging;
	}
	public void setTeach_aging(Integer teach_aging) {
		this.teach_aging = teach_aging;
	}
	public Integer getDevice_type() {
		return device_type;
	}
	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getService_count() {
		return service_count;
	}
	public void setService_count(Integer service_count) {
		this.service_count = service_count;
	}
	public String getTraining_start_time() {
		return training_start_time;
	}
	public void setTraining_start_time(String training_start_time) {
		this.training_start_time = training_start_time;
	}
	public String getTraining_end_time() {
		return training_end_time;
	}
	public void setTraining_end_time(String training_end_time) {
		this.training_end_time = training_end_time;
	}
}
