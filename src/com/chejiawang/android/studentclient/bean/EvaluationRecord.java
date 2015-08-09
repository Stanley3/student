package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
import java.sql.Date;

public class EvaluationRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer evaluation_record_id;
	private Integer student_id;
	private Integer coach_id;
	private Integer order_id;
	private String content;
	private Integer skill;
	private Integer service_attitude;
	private Integer hygiene;
	private Date evaluation_time;
	private Integer evaluation_type;
	private Integer validation;
	private Integer evaluation;
	private String operation;
	private Integer operator;
	
	//学员评价界面额外需要的字段
	private String coach_name;
	private String phone;
	private String course_status;
	
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCourse_status() {
		return course_status;
	}
	public void setCourse_status(String course_status) {
		this.course_status = course_status;
	}
	public Integer getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}
	public void setEvaluation_record_id(Integer evaluation_record_id){
		this.evaluation_record_id = evaluation_record_id;
	}
	public Integer getEvaluation_record_id() {
		return evaluation_record_id;
	}
	public void setStudent_id(Integer student_id){
		this.student_id = student_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setCoach_id(Integer coach_id){
		this.coach_id = coach_id;
	}
	public Integer getCoach_id() {
		return coach_id;
	}
	public void setOrder_id(Integer order_id){
		this.order_id = order_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setSkill(Integer skill){
		this.skill = skill;
	}
	public Integer getSkill() {
		return skill;
	}
	public void setService_attitude(Integer service_attitude){
		this.service_attitude = service_attitude;
	}
	public Integer getService_attitude() {
		return service_attitude;
	}
	public void setHygiene(Integer hygiene){
		this.hygiene = hygiene;
	}
	public Integer getHygiene() {
		return hygiene;
	}
	public void setEvaluation_time(Date evaluation_time){
		this.evaluation_time = evaluation_time;
	}
	public Date getEvaluation_time() {
		return evaluation_time;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
	public Integer getEvaluation_type() {
		return evaluation_type;
	}
	public void setEvaluation_type(Integer evaluation_type) {
		this.evaluation_type = evaluation_type;
	}
}
