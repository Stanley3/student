package com.chejiawang.android.studentclient.network;

import com.chejiawang.android.studentclient.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 用来解析预约列表
 * 
 * @author Administrator
 *
 */
public class HomeApi {
	public static final String TAG = "HomeApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "schoolInfo/getSchoolInfoByDistance";
	public static void getSchoolList(double longitude, double latitude, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("longitude", longitude);
		params.put("latitude", latitude);
		
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
