package com.chejiawang.android.studentclient.home.coach;

import java.io.InputStream;
import java.io.Serializable;

import android.view.View;

import com.chejiawang.android.studentclient.base.BaseRecycleViewFragment;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.SelectCoach;
import com.chejiawang.android.studentclient.utils.ListEntity;



public class CoachFragment extends BaseRecycleViewFragment {

	public int school_id;
	public int subject_id;
	public int order;

	@Override
	protected void initViews(View view) {
		initRequestInfo();
		super.initViews(view);
	}
	
	public void initRequestInfo() {
		//此处需要初始化请求参数信息
//		this.student_id = ((StudentLoginSuccessInfo)AppContext.getInfo("coach_info")).getStudent_id();
//		this.sort = "order_time";
//		this.order = "desc";
//		this.option = 2;
	}
	protected RecycleBaseAdapter getListAdapter() {
		return new CoachOrderAdapter();
	}
	@Override
	protected String getCacheKeyPrefix() {
		return null;
	}
	protected ListEntity parseList(InputStream is) throws Exception{
		CoachList list = CoachList.parse(is);
		return list;
	}
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (CoachList)seri;
	}
	@Override
	protected void sendRequestData() {
	//	OrderApi.getOrderList(student_id, mCurrentPage, sort, option, order, getResponseHandler());
	}
	@Override
	public void onItemClick(View view, int position) {
		SelectCoach coachInfo = (SelectCoach) mAdapter.getItem(position);
		if(coachInfo != null){
			
		}
	}
	
}
