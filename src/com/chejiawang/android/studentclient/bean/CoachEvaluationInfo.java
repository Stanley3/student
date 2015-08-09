package com.chejiawang.android.studentclient.bean;


/**
 * 
 * @author gujh
 * <p>App教练端 我的评价 界面用到的bean
 */
public class CoachEvaluationInfo {
	private Integer evaluation_record_id;
	private Integer student_id;
	private Integer coach_id;
	private Integer order_id;
	private String content;
	private Integer skill;
	private Integer service_attitude;
	private Integer hygiene;
	private String evaluation_time;
	private Integer evaluation_type;
	private Integer evaluation;	
	private Integer course_status;
	private String queryTime;
	
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public Integer getEvaluation_record_id() {
		return evaluation_record_id;
	}
	public void setEvaluation_record_id(Integer evaluation_record_id) {
		this.evaluation_record_id = evaluation_record_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSkill() {
		return skill;
	}
	public void setSkill(Integer skill) {
		this.skill = skill;
	}
	public Integer getService_attitude() {
		return service_attitude;
	}
	public void setService_attitude(Integer service_attitude) {
		this.service_attitude = service_attitude;
	}
	public Integer getHygiene() {
		return hygiene;
	}
	public void setHygiene(Integer hygiene) {
		this.hygiene = hygiene;
	}
	public String getEvaluation_time() {
		return evaluation_time;
	}
	public void setEvaluation_time(String evaluation_time) {
		this.evaluation_time = evaluation_time;
	}
	public Integer getEvaluation_type() {
		return evaluation_type;
	}
	public void setEvaluation_type(Integer evaluation_type) {
		this.evaluation_type = evaluation_type;
	}
	public Integer getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

}
