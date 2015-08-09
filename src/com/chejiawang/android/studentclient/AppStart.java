package com.chejiawang.android.studentclient;

import com.chejiawang.android.studentclient.app.AppContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 启动界面
 * @author Xy.Sun
 *
 */
public class AppStart extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_app_start, null); 
		setContentView(view);
		AlphaAnimation alp = new AlphaAnimation(0.3f, 1.0f);
		alp.setDuration(2000);
		view.startAnimation(alp);
		alp.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				redirectTo();
				
			}
		});
		
		AppContext appContext = (AppContext) getApplication();
		String cookie = appContext.getProperty("cookie");
	}

	protected void redirectTo() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
