package com.chejiawang.android.studentclient.network;

import java.util.Locale;

import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.app.Logger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiHttpClient {

	public static String TAG = "ApiHttpClient";
	private static String API_URL;
	/**
	 * 初始的URL 非常重要
	 */
	public static String DEV_API_URL = "http://120.26.61.165/DTAServer/rest/%s"; // 需要些的URL

	public static AsyncHttpClient client;
	public final static String HOST = "120.26.61.165"; // 主机名，待添加
	private static String appCookie;

	public static String getAppCookie() {
		return appCookie;
	}

	public static void setAppCookie(String appCookie) {
		ApiHttpClient.appCookie = appCookie;
	}

	static {
		API_URL = DEV_API_URL;
	}

	public static void setHttpClient(AsyncHttpClient c) {
		client = c;
		client.addHeader("Accept-Language", Locale.getDefault().toString());
		client.addHeader("Host", HOST);
		client.addHeader("Connection", "Keep-Alive");
		setUserAgent(ApiClientHelper.getUserAgent(AppContext.instance()));
	}

	public static void setUserAgent(String userAgent) {
		client.setUserAgent(userAgent);
	}

	public static String getCookie(AppContext appContext) {
		if (appCookie == null || appCookie == "") {
			appCookie = AppContext.getCookie();
		}
		return appCookie;
	}

	public static void cleanCookie() {
		appCookie = "";
	}

	public static void setCookie(String cookie) {
		client.addHeader("Cookie", cookie);
	}

	public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
		// String url =
		// "http://120.26.61.165/DTAServer/rest/orderRecord/getCoachPrecontractedRecord?coach_id=1&sort=order_time&dir=desc&start=0&length=10&order_Status=3";
		client.get(getAbsoluteApiUrl(partUrl), params, handler);
	}

	public static void post(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
		// Logger.e(TAG, getAbsoluteApiUrl(partUrl));
		client.post(getAbsoluteApiUrl(partUrl), params, handler);

	}

	public static String getAbsoluteApiUrl(String partUrl) {
		String url = String.format(API_URL, partUrl);
		Logger.e(TAG, "request: " + url);
		return url;
	}
}
