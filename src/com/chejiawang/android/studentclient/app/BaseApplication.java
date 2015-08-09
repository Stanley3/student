package com.chejiawang.android.studentclient.app;

import com.chejiawang.android.studentclient.R;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseApplication extends Application {
	
	private static String PREF_NAME = "creativelockerV2.pref";
	public static Context _context;
	public static Resources _resource;
	
	private static String lastToast = "";
	private static long lastToastTime;
	
	
	private static boolean sIsAtLeastGB;
	static{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
			sIsAtLeastGB = true;
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		_context = getApplicationContext();
		_resource = _context.getResources();
		init();
	}
	/**
	 * 初始化代码
	 */
	protected void init(){
		
	};
	
	public static synchronized BaseApplication context(){
		return (BaseApplication) _context;
	}
	
	public static SharedPreferences getPreferences() {
		SharedPreferences sp = context().getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
		return sp;
	}
	
	public static void apply(Editor editor){
		if(sIsAtLeastGB){
			editor.apply();
		}else{
			editor.commit();
		}
	}

	public static void showToashShort(String message) {
		showToast(message, Toast.LENGTH_SHORT, 0, Gravity.FILL_HORIZONTAL| Gravity.TOP);	
	}
	public static void showToast(String message, int duration, int icon, int gravity) {
	       if (message != null && !message.equalsIgnoreCase("")) {
	            long time = System.currentTimeMillis();
	            if (!message.equalsIgnoreCase(lastToast)
	                    || Math.abs(time - lastToastTime) > 2000) {

	                View view = LayoutInflater.from(context()).inflate(
	                        R.layout.v2_view_toast, null);
	                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
	                if (icon != 0) {
	                    ((ImageView) view.findViewById(R.id.icon_iv))
	                            .setImageResource(icon);
	                    ((ImageView) view.findViewById(R.id.icon_iv))
	                            .setVisibility(View.VISIBLE);
	                }
	                Toast toast = new Toast(context());
	                toast.setView(view);
	                //toast.setGravity(gravity, 0 , TDevice.getActionBarHeight(context()));
	                // getToastMarignBottom()
	                // toast.setGravity(Gravity.TOP|Gravity.LEFT,0 ,0);
	                toast.setDuration(duration);
	                toast.show();

	                lastToast = message;
	                lastToastTime = System.currentTimeMillis();
	            }
	        }
	}
}
