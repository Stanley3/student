package com.chejiawang.android.studentclient.bean;

import java.util.Map;

public class Entity extends Base {
	
	protected int id;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	protected String cacheKey;
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	
}
