package com.chejiawang.android.studentclient.bean;

public class CoachFianceSummarizing {
	private Integer order_id;
	private Integer order_status;
	private Integer course_status;
	private Integer student_level;
	private Double order_amount;
	private Integer coach_id;
	
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
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public Integer getStudent_level() {
		return student_level;
	}
	public void setStudent_level(Integer student_level) {
		this.student_level = student_level;
	}
	public Double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}
}
