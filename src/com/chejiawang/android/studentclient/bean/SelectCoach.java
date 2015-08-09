package com.chejiawang.android.studentclient.bean;

public class SelectCoach {
	private Integer coach_id;
	private Integer school_id;
	private Integer schedule_id;
	private String coach_name;
	private String phone;
	private Integer star;
	private Integer service_count;
	private Integer teaching_age;
	private Integer online;//是否在线
	private Integer device_type;
	private Integer havePhoto;
	
	public Integer getHavePhoto() {
		return havePhoto;
	}
	public void setHavePhoto(Integer havePhoto) {
		this.havePhoto = havePhoto;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(Integer coach_id) {
		this.coach_id = coach_id;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public Integer getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Integer schedule_id) {
		this.schedule_id = schedule_id;
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
	public Integer getTeaching_age() {
		return teaching_age;
	}
	public void setTeaching_age(Integer teaching_age) {
		this.teaching_age = teaching_age;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getDevice_type() {
		return device_type;
	}
	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}
}

