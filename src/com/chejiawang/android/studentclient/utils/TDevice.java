package com.chejiawang.android.studentclient.utils;


import com.chejiawang.android.studentclient.app.BaseApplication;

import android.net.ConnectivityManager;
import android.os.Build;

public class TDevice {
	public static boolean GTE_HC;
	public static boolean GTE_ICS;
	public static boolean PRE_HC;
	private static int _pageSize = -1;
	
	static{
		GTE_ICS = Build.VERSION.SDK_INT >= 14;
		GTE_HC = Build.VERSION.SDK_INT >= 11;
		PRE_HC = Build.VERSION.SDK_INT <= 11;
	}
	public TDevice(){
		
	}
	

	public static void set_pageSize(int _pageSize) {
		TDevice._pageSize = _pageSize;
	}

	public static boolean hasInternet(){
		boolean flag;
		if(((ConnectivityManager)BaseApplication.context().getSystemService("connectivity")).getActiveNetworkInfo() != null){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}

	public static int getPageSize() {
		if(_pageSize == -1){
			_pageSize = 15;
		}
		return _pageSize;
	}
}
