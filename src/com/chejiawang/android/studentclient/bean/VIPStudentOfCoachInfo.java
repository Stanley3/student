package com.chejiawang.android.studentclient.bean;

public class VIPStudentOfCoachInfo {
	private Integer coach_id;
	private Integer student_id;
	private String student_name;
	private Integer student_level;
	private String student_phone;
	private String begin_vip_date;
	private Integer training_times;
	
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Integer getStudent_level() {
		return student_level;
	}
	public void setStudent_level(Integer student_level) {
		this.student_level = student_level;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getBegin_vip_date() {
		return begin_vip_date;
	}
	public void setBegin_vip_date(String begin_vip_date) {
		this.begin_vip_date = begin_vip_date;
	}
	public Integer getTraining_times() {
		return training_times;
	}
	public void setTraining_times(Integer training_times) {
		this.training_times = training_times;
	}
}
