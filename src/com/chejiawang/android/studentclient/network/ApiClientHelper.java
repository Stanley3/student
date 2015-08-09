package com.chejiawang.android.studentclient.network;

import com.chejiawang.android.studentclient.app.AppContext;


public class ApiClientHelper {
	public static String baseUrl = "120.26.61.165";
	public static String getUserAgent(AppContext appContext){
		StringBuilder ua = new StringBuilder(baseUrl);
		ua.append("/" + appContext.getPackageInfo().versionName + "_" + "29");
		ua.append("/Android");
		ua.append("/" + android.os.Build.VERSION.RELEASE);
		ua.append("/" + android.os.Build.MODEL);
		ua.append("/" + appContext.getAppId());
		return ua.toString();
	}
}
