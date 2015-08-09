package com.chejiawang.android.studentclient.bean;

public class LatestNumber {
	private Integer newPrecotractNumber; //最新预约的个数
	private Integer newCancelNumber; //最新取消预约的个数
	private Integer newEvaluationNumber;//最新评价的个数
	
	public Integer getNewPrecotractNumber() {
		return newPrecotractNumber;
	}
	public void setNewPrecotractNumber(Integer newPrecotractNumber) {
		this.newPrecotractNumber = newPrecotractNumber;
	}
	public Integer getNewCancelNumber() {
		return newCancelNumber;
	}
	public void setNewCancelNumber(Integer newCancelNumber) {
		this.newCancelNumber = newCancelNumber;
	}
	public Integer getNewEvaluationNumber() {
		return newEvaluationNumber;
	}
	public void setNewEvaluationNumber(Integer newEvaluationNumber) {
		this.newEvaluationNumber = newEvaluationNumber;
	}
}
