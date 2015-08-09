package com.chejiawang.android.studentclient.home;

import java.io.InputStream;
import java.io.Serializable;

import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.base.BaseRecycleViewFragment;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.SchoolInfoBasedDistance;
import com.chejiawang.android.studentclient.bean.StudentLoginSuccessInfo;
import com.chejiawang.android.studentclient.network.HomeApi;
import com.chejiawang.android.studentclient.utils.ListEntity;

import android.view.View;
import android.widget.Toast;
/**
 * 主页中第二个页面
 * 
 * @author 孙晓雨
 *
 */
public class HomeInFragment2 extends BaseRecycleViewFragment{
	private String TAG = "HomeInFragment2";
	private static final String CACHE_KEY_PREFIX = "accordDistance_";
	/**请求参数**/
	public int student_id = 1;
	public String sort = "order_time";
	public int option = 1;
	public String order = "desc";
	public double longitude = 100l;
	public double latitude = 100l;
	
	@Override
	protected void initViews(View view) {
		initRequestInfo();
		super.initViews(view);
	}
	
	public void initRequestInfo() {
		//此处需要初始化请求参数信息
//		this.student_id = ((StudentLoginSuccessInfo)AppContext.getInfo("student_info")).getStudent_id();
		this.longitude = 0;
		this.latitude = 0;
	}
	@Override
	protected RecycleBaseAdapter getListAdapter() {
		return new DistanceRecycleAdapter();
	}
	
	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX;
	}
	
	protected ListEntity parseList(InputStream is) throws Exception{
		SchoolInfoList list = SchoolInfoList.parse(is);
		return list;
	}
	
	@Override
	protected ListEntity readList(Serializable seri) {	
		return (SchoolInfoList)seri;
	}
	
	@Override
	protected void sendRequestData() {
		HomeApi.getSchoolList(longitude, latitude, getResponseHandler());
	//	OrderApi.getOrderList(student_id, mCurrentPage, sort, option, order, getResponseHandler());
	}
	@Override
	public void onItemClick(View view, int position) {
		SchoolInfoBasedDistance info = (SchoolInfoBasedDistance) mAdapter.getItem(position);
		if(info != null){
			Logger.e(TAG, position + "被点击");
		}
	}
	
}
