package com.chejiawang.android.studentclient.bean;

/**
 * 
 * @author gujh
 * <p>教练被预约的信息记录
 */
public class CoachPrecontractRecord {
	private String student_name;
	private String student_phone;
	private Integer student_id;
	private Integer status;//学员vip信息
	private Integer coach_id;
	private Integer order_id;
	private Integer school_id;
	private Integer course_status;//科目信息
	private String order_time;
	private String training_start_time;
	private String training_end_time;
	private String order_status;//订单信息
	private String queryTime; // 本次查询的时间
	
	
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
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
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	
}
