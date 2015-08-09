package com.chejiawang.android.studentclient.bean;

import java.io.Serializable;
public class SysRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer role_id;
	private String role_name;
	private Integer role_permission;
	private Integer validation;
	public void setRole_id(Integer role_id){
		this.role_id = role_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_name(String role_name){
		this.role_name = role_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_permission(Integer role_permission){
		this.role_permission = role_permission;
	}
	public Integer getRole_permission() {
		return role_permission;
	}
	public void setValidation(Integer validation){
		this.validation = validation;
	}
	public Integer getValidation() {
		return validation;
	}
}
