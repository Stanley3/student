package com.chejiawang.android.studentclient.home.coach;

public class CompreHensiveFragment extends CoachFragment{

	public String CACHE_KEY_PREFIX = "compreHensive_info_list";
	
	public void initRequestInfo() {
		//此处需要初始化请求参数信息
//		this.student_id = ((StudentLoginSuccessInfo)AppContext.getInfo("coach_info")).getStudent_id();
//		this.sort = "order_time";
//		this.order = "desc";
//		this.option = 2;
	}
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	
}
