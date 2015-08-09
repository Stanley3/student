package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
public class SchoolInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer school_id;
	private String school_name;
	private String contract_no;
	private String leader;
	private String leader_phone;
	private String school_address;
	private String school_phone;
	private Double longitude;
	private Double latitude;
	private Integer coach_number;
	private Integer subject_2_number;
	private Integer subject_3_number;
	private Integer validation;
	private String register_time;
	private Integer register_number;
	private Integer student_number;
	private Integer school_type;
	private Integer status;
	private Double prepaid;
	private String bank_account;
	private String account_holder;
	private Double subject_2_fee;
	private Double subject_3_fee;
	private Double charter_2_fee;
	private Double charter_3_fee;
	private Integer device_2_status;
	private Integer device_3_status;
	private Integer vehicle_number;
	
	public Integer getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(Integer vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	public void setSchool_id(Integer school_id){
		this.school_id = school_id;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_name(String school_name){
		this.school_name = school_name;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setContract_no(String contract_no){
		this.contract_no = contract_no;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setLeader(String leader){
		this.leader = leader;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader_phone(String leader_phone){
		this.leader_phone = leader_phone;
	}
	public String getLeader_phone() {
		return leader_phone;
	}
	public void setSchool_address(String school_address){
		this.school_address = school_address;
	}
	public String getSchool_address() {
		return school_address;
	}
	public void setSchool_phone(String school_phone){
		this.school_phone = school_phone;
	}
	public String getSchool_phone() {
		return school_phone;
	}
	public void setCoach_number(Integer coach_number){
		this.coach_number = coach_number;
	}
	public Integer getCoach_number() {
		return coach_number;
	}
	public void setSubject_2_number(Integer subject_2_number){
		this.subject_2_number = subject_2_number;
	}
	public Integer getSubject_2_number() {
		return subject_2_number;
	}
	public void setSubject_3_number(Integer subject_3_number){
		this.subject_3_number = subject_3_number;
	}
	public Integer getSubject_3_number() {
		return subject_3_number;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
	public void setRegister_time(String register_time){
		this.register_time = register_time;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_number(Integer register_number){
		this.register_number = register_number;
	}
	public Integer getRegister_number() {
		return register_number;
	}
	public void setStudent_number(Integer student_number){
		this.student_number = student_number;
	}
	public Integer getStudent_number() {
		return student_number;
	}
	public void setSchool_type(Integer school_type){
		this.school_type = school_type;
	}
	public Integer getSchool_type() {
		return school_type;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setBank_account(String bank_account){
		this.bank_account = bank_account;
	}
	public String getBank_account() {
		return bank_account;
	}
	public String getAccount_holder() {
		return account_holder;
	}
	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}
	public void setDevice_2_status(Integer device_2_status){
		this.device_2_status = device_2_status;
	}
	public Integer getDevice_2_status() {
		return device_2_status;
	}
	public void setDevice_3_status(Integer device_3_status){
		this.device_3_status = device_3_status;
	}
	public Integer getDevice_3_status() {
		return device_3_status;
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
	public Double getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(Double prepaid) {
		this.prepaid = prepaid;
	}
	public Double getSubject_2_fee() {
		return subject_2_fee;
	}
	public void setSubject_2_fee(Double subject_2_fee) {
		this.subject_2_fee = subject_2_fee;
	}
	public Double getSubject_3_fee() {
		return subject_3_fee;
	}
	public void setSubject_3_fee(Double subject_3_fee) {
		this.subject_3_fee = subject_3_fee;
	}
	public Double getCharter_2_fee() {
		return charter_2_fee;
	}
	public void setCharter_2_fee(Double charter_2_fee) {
		this.charter_2_fee = charter_2_fee;
	}
	public Double getCharter_3_fee() {
		return charter_3_fee;
	}
	public void setCharter_3_fee(Double charter_3_fee) {
		this.charter_3_fee = charter_3_fee;
	}
	
}
