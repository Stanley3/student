package com.chejiawang.android.studentclient.bean;

public class GatherStudentInfo {
	private Integer student_id;
	private Integer subject_2_passed;
	private Integer subject_3_passed;
	private String student_name;
	private Integer course_status;
	private String training_start_time;
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getSubject_2_passed() {
		return subject_2_passed;
	}
	public void setSubject_2_passed(Integer subject_2_passed) {
		this.subject_2_passed = subject_2_passed;
	}
	public Integer getSubject_3_passed() {
		return subject_3_passed;
	}
	public void setSubject_3_passed(Integer subject_3_passed) {
		this.subject_3_passed = subject_3_passed;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public String getTraining_start_time() {
		return training_start_time;
	}
	public void setTraining_start_time(String training_start_time) {
		this.training_start_time = training_start_time;
	}
	
	
}
