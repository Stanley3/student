package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
import java.util.Date;

public class StudentDepositRecord implements Serializable{
	private Integer deposit_record_id;
	private Integer student_id;
	private String deposit_amount;
	private String depositor_name;
	private Integer depositor_id;
	private Integer deposit_way;
	private String source_no;
	private Date deposit_date;
	private Integer validation;
	private Integer chartered_driving_school;
	private Integer chartered_coach;
	private Integer deposit_type;
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getChartered_driving_school() {
		return chartered_driving_school;
	}
	public void setChartered_driving_school(Integer chartered_driving_school) {
		this.chartered_driving_school = chartered_driving_school;
	}
	public Integer getChartered_coach() {
		return chartered_coach;
	}
	public void setChartered_coach(Integer chartered_coach) {
		this.chartered_coach = chartered_coach;
	}
	public void setDeposit_record_id(Integer deposit_record_id){
		this.deposit_record_id = deposit_record_id;
	}
	public Integer getDeposit_record_id() {
		return deposit_record_id;
	}
	public void setStudent_id(Integer student_id){
		this.student_id = student_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setDeposit_amount(String deposit_amount){
		this.deposit_amount = deposit_amount;
	}
	public String getDeposit_amount() {
		return deposit_amount;
	}
	public void setDepositor_name(String depositor_name){
		this.depositor_name = depositor_name;
	}
	public String getDepositor_name() {
		return depositor_name;
	}
	public void setDepositor_id(Integer depositor_id){
		this.depositor_id = depositor_id;
	}
	public Integer getDepositor_id() {
		return depositor_id;
	}
	public void setDeposit_way(Integer deposit_way){
		this.deposit_way = deposit_way;
	}
	public Integer getDeposit_way() {
		return deposit_way;
	}
	public void setSource_no(String source_no){
		this.source_no = source_no;
	}
	public String getSource_no() {
		return source_no;
	}
	public void setDeposit_date(Date deposit_date){
		this.deposit_date = deposit_date;
	}
	public Date getDeposit_date() {
		return deposit_date;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
	public Integer getDeposit_type() {
		return deposit_type;
	}
	public void setDeposit_type(Integer deposit_type) {
		this.deposit_type = deposit_type;
	}
	
}
