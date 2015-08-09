package com.chejiawang.android.studentclient.bean;

public class DisplayStudentDepositReocrd {
	private Integer deposit_record_id;
	private String deposit_date;
	private String deposit_address;//暂时为null
	private Double deposit_amount;
	private Integer deposit_way;
	private Integer deposit_type;
	
	
	public Integer getDeposit_record_id() {
		return deposit_record_id;
	}
	public void setDeposit_record_id(Integer deposit_record_id) {
		this.deposit_record_id = deposit_record_id;
	}
	public String getDeposit_date() {
		return deposit_date;
	}
	public void setDeposit_date(String deposit_date) {
		this.deposit_date = deposit_date;
	}
	public String getDeposit_address() {
		return deposit_address;
	}
	public void setDeposit_address(String deposit_address) {
		this.deposit_address = deposit_address;
	}
	public Double getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public Integer getDeposit_way() {
		return deposit_way;
	}
	public void setDeposit_way(Integer deposit_way) {
		this.deposit_way = deposit_way;
	}
	public Integer getDeposit_type() {
		return deposit_type;
	}
	public void setDeposit_type(Integer deposit_type) {
		this.deposit_type = deposit_type;
	}
}
