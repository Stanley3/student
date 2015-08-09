package com.chejiawang.android.studentclient.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class UIHelper {


	public static void startActivityUtil(Context context, Class<?> clazz){
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}


	/**
	 * 弹出消息
	 * @param context
	 * @param msg
	 */
	public static void ToastMessage(Context context,String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
