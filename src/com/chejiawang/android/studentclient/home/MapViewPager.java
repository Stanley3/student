package com.chejiawang.android.studentclient.home;

import com.chejiawang.android.studentclient.app.Logger;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


public class MapViewPager extends ViewPager {

	private String TAG = "MapViewPager";
	public MapViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MapViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	 protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
		Logger.e(TAG, v.getClass().getName());
        if(v.getClass().getName().equals("com.baidu.mapapi.map.MapView")) {    
            return true;    
        }
        return super.canScroll(v, checkV, dx, x, y);   
   }   
}
