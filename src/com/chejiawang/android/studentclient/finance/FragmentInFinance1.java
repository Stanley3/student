package com.chejiawang.android.studentclient.finance;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.base.BaseRecycleViewFragment;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.DisplayStudentDepositReocrd;
import com.chejiawang.android.studentclient.bean.StudentLoginSuccessInfo;
import com.chejiawang.android.studentclient.network.FinanceApi;
import com.chejiawang.android.studentclient.utils.ListEntity;

import android.view.View;
/**
 * 消费记录
 * @author Administrator
 *
 */
public class FragmentInFinance1 extends BaseRecycleViewFragment {

	private String TAG = "FragmentInFinance1";
	private static final String CACHE_KEY_PREFIX = "cost_log_";
	/**请求参数**/
	public int student_id = 1;
	

	@Override
	protected void initViews(View view) {
		initRequestInfo();
		super.initViews(view);
	}
	public void initRequestInfo() {
		//此处需要初始化请求参数信息
		this.student_id = ((StudentLoginSuccessInfo)AppContext.getInfo("student_info")).getStudent_id();
	}
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new CostLogAdapter();
	}
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	protected ListEntity parseList(InputStream is) throws Exception{
		CoatList list = CoatList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (CoatList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		FinanceApi.getCostList(student_id, mCurrentPage, getResponseHandler());
	}
	
	@Override
	public void onItemClick(View view, int position) {
		DisplayStudentDepositReocrd record = (DisplayStudentDepositReocrd) mAdapter.getItem(position);
		if(record != null){
			
		}
		super.onItemClick(view);
	}
}
