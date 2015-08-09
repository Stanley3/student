package com.chejiawang.android.studentclient.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author gujh
 * 查看详细评价界面需要的bean
 *
 */
public class EvaluationAndOrderInfo {
	private String student_id;
	private String student_name;
	private String student_phone;
	private String student_school_name;
	private String student_level;
	private String coach_id;
	private String coach_name;
	private String coach_phone;
	private String coach_school_name;
	private String order_id;
	private Double order_amount;
	private String training_start_time;
	private String training_end_time;
	private String course_status;
	private String order_status;
	private String evaluation_record_id;
	private String evaluation_time;
	private String skill;
	private String service_attitude;
	private String hygiene;
	private String content;
	private String evaluation_type;
	private String evaluation;
	private String validation;
	private String trainingTime;
	private String evaluation_operation;
	private String order_operation;
	
	public String getEvaluation_operation() {
		return evaluation_operation;
	}
	public void setEvaluation_operation(String evaluation_operation) {
		this.evaluation_operation = evaluation_operation;
	}
	public String getOrder_operation() {
		return order_operation;
	}
	public void setOrder_operation(String order_operation) {
		this.order_operation = order_operation;
	}
	public String getStudent_level() {
		return student_level;
	}
	public void setStudent_level(String student_level) {
		this.student_level = student_level;
	}
	public String getTrainingTime() {
		return trainingTime;
	}
	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getStudent_school_name() {
		return student_school_name;
	}
	public void setStudent_school_name(String student_school_name) {
		this.student_school_name = student_school_name;
	}
	public String getCoach_id() {
		return coach_id;
	}
	public void setCoach_id(String coach_id) {
		this.coach_id = coach_id;
	}
	public String getCoach_name() {
		return coach_name;
	}
	public void setCoach_name(String coach_name) {
		this.coach_name = coach_name;
	}
	public String getCoach_phone() {
		return coach_phone;
	}
	public void setCoach_phone(String coach_phone) {
		this.coach_phone = coach_phone;
	}
	public String getCoach_school_name() {
		return coach_school_name;
	}
	public void setCoach_school_name(String coach_school_name) {
		this.coach_school_name = coach_school_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}
	public String getTraining_start_time() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			training_start_time = format.format(format.parse(training_start_time));
		}catch(Exception e){
			e.printStackTrace();
		}
		return training_start_time;
	}
	public void setTraining_start_time(String training_start_time) {
		this.training_start_time = training_start_time;
	}
	public String getTraining_end_time() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			training_end_time = format.format(format.parse(training_end_time));
		}catch(Exception e){
			e.printStackTrace();
		}
		return training_end_time;
	}
	public void setTraining_end_time(String training_end_time) {
		this.training_end_time = training_end_time;
	}
	public String getCourse_status() {
		return course_status;
	}
	public void setCourse_status(String course_status) {
		this.course_status = course_status;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	public String getEvaluation_record_id() {
		return evaluation_record_id;
	}
	public void setEvaluation_record_id(String evaluation_record_id) {
		this.evaluation_record_id = evaluation_record_id;
	}
	public String getEvaluation_time() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			evaluation_time = format.format(format.parse(evaluation_time));
		}catch(Exception e){
			e.printStackTrace();
		}
		return evaluation_time;
	}
	public void setEvaluation_time(String evaluation_time) {
		this.evaluation_time = evaluation_time;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getService_attitude() {
		return service_attitude;
	}
	public void setService_attitude(String service_attitude) {
		this.service_attitude = service_attitude;
	}
	public String getHygiene() {
		return hygiene;
	}
	public void setHygiene(String hygiene) {
		this.hygiene = hygiene;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvaluation_type() {
		return evaluation_type;
	}
	public void setEvaluation_type(String evaluation_type) {
		this.evaluation_type = evaluation_type;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
}
