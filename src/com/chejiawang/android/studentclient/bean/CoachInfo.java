package com.chejiawang.android.studentclient.bean;

public class CoachInfo {
	private Integer coach_id;
	private String coach_name;
	private String school_name;
	private Integer star;
	private Integer validation;
	private String phone;
	private Integer teaching_age;
	private Integer teachingTimes;
	
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
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getValidation() {
		return validation;
	}
	public void setValidation(Integer validation) {
		this.validation = validation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getTeaching_age() {
		return teaching_age;
	}
	public void setTeaching_age(Integer teaching_age) {
		this.teaching_age = teaching_age;
	}
	public Integer getTeachingTimes() {
		return teachingTimes;
	}
	public void setTeachingTimes(Integer teachingTimes) {
		this.teachingTimes = teachingTimes;
	}
}
