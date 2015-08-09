package com.chejiawang.android.studentclient.network;

import com.chejiawang.android.studentclient.utils.TDevice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FinanceApi {

	public static final String TAG = "FinanceApi";
	public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
	public static final String PART_URL = "studentDepositRecord/getStudentDepositRecord";
	
	public static void getCostList(int student_id, int start, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("student_id", student_id);
		params.put("start", start);
		params.put("length", DEF_PAGE_SIZE);
		ApiHttpClient.get(PART_URL, params, handler);
	}
}
