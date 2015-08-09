package com.chejiawang.android.studentclient.bean;

public class SchoolInfoBasedDistance {
	private Integer school_id;
	private String school_name;
	private String school_address;
	private String school_type;
	private Integer device_2_status;
	private Integer device_3_status;
	private Integer subject_2_number;
	private Integer subject_3_number;
	private Integer vehicle_number;
	private Integer register_number;
	private Double distance;
	private  Double longitude; //经度
	private Double latitude; //纬度
	
	public String getSchool_address() {
		return school_address;
	}
	public void setSchool_address(String school_address) {
		this.school_address = school_address;
	}
	public String getSchool_type() {
		return school_type;
	}
	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getDevice_2_status() {
		return device_2_status;
	}
	public void setDevice_2_status(Integer device_2_status) {
		this.device_2_status = device_2_status;
	}
	public Integer getDevice_3_status() {
		return device_3_status;
	}
	public void setDevice_3_status(Integer device_3_status) {
		this.device_3_status = device_3_status;
	}
	public Integer getSubject_2_number() {
		return subject_2_number;
	}
	public void setSubject_2_number(Integer subject_2_number) {
		this.subject_2_number = subject_2_number;
	}
	public Integer getSubject_3_number() {
		return subject_3_number;
	}
	public void setSubject_3_number(Integer subject_3_number) {
		this.subject_3_number = subject_3_number;
	}
	public Integer getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(Integer vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	public Integer getRegister_number() {
		return register_number;
	}
	public void setRegister_number(Integer register_number) {
		this.register_number = register_number;
	}
}
