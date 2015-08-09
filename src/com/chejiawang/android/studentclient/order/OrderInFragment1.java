package com.chejiawang.android.studentclient.order;

import java.io.InputStream;
import java.io.Serializable;

import android.view.View;

import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.base.BaseRecycleViewFragment;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.MyOrderRecord;
import com.chejiawang.android.studentclient.bean.StudentLoginSuccessInfo;
import com.chejiawang.android.studentclient.network.OrderApi;
import com.chejiawang.android.studentclient.utils.ListEntity;


public class OrderInFragment1 extends BaseRecycleViewFragment {
	private String TAG = "OrderInFragment1";
	private static final String CACHE_KEY_PREFIX = "haveOrder_";
	/**请求参数**/
	public int student_id = 1;
	public String sort = "order_time";
	public int option = 1;
	public String order = "desc";
	
	@Override
	protected void initViews(View view) {
		initRequestInfo();
		super.initViews(view);
	}
	
	public void initRequestInfo() {
		//此处需要初始化请求参数信息
		this.student_id = ((StudentLoginSuccessInfo)AppContext.getInfo("student_info")).getStudent_id();
		this.sort = "order_time";
		this.order = "desc";
		this.option = 1;
	}

	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new HaveOrderRecycleAdapter();
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		HaveOrderList list = HaveOrderList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (HaveOrderList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		OrderApi.getOrderList(student_id, mCurrentPage, sort, option, order, getResponseHandler());
	}

	@Override
	public void onItemClick(View view, int position) {
		MyOrderRecord haveOrder = (MyOrderRecord) mAdapter.getItem(position);
		if(haveOrder != null){
			
		}
		super.onItemClick(view);
	}
}
