package com.chejiawang.android.studentclient.order;

import java.util.ArrayList;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.app.Logger;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 预约页的Fragment
 * 
 * @author sunxiaoyu
 * 
 */
public class OrderFragment extends Fragment implements OnClickListener {
	String TAG = "OrderFragment";
	/**存放两个Fragment的容器**/
	private ViewPager mPager;
	
	private ArrayList<Fragment> fragmentsList;
	private Button bt_rechang, bt_practice;
	private Button bt_back;
	Fragment order1, order2;
	Resources resources;

	/**
	 * 按钮下切换的下划线
	 */
	private ImageView iv_line;
	
	private int currIndex = 0;
	/**
	 * 线宽
	 */
	int lineWidth = 0;
	/**
	 * 按钮的数量
	 */
	private int buttonNum = 2;
	/**
	 * 资源文件
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		resources = getResources();
		initWidth();
		InitButton(view);
		InitViewPager(view);
		bt_rechang.setTextColor(resources.getColor(R.color.bt_background_selected));
		Logger.e(TAG, "onCreateView");
		return view;
	}
	private void initWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        lineWidth = screenW / buttonNum;
		
	}
	private void InitButton(View parentView) {
		bt_rechang = (Button) parentView.findViewById(R.id.bt_rechange);
		bt_practice = (Button) parentView.findViewById(R.id.bt_practice);
		bt_back = (Button) parentView.findViewById(R.id.bt_user_info);
		iv_line = (ImageView) parentView.findViewById(R.id.iv_line);

		bt_rechang.setOnClickListener(this);
		bt_practice.setOnClickListener(this);
		bt_back.setOnClickListener(this);
	}
	
	private void InitViewPager(View parentView) {
		Logger.e(TAG, "InitViewPager");
		mPager = (ViewPager) parentView.findViewById(R.id.vp_order);
		fragmentsList = new ArrayList<Fragment>();

		order1 = new OrderInFragment1();
		order2 = new OrderInFragment2();

		fragmentsList.add(order1);
		fragmentsList.add(order2);

		mPager.setAdapter(new OrderFragmentPagerAdapter(
				getChildFragmentManager(), fragmentsList));
//		mPager.setAdapter(new OrderFragmentPagerAdapter(getChildFragmentManager()));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);

	}



	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_practice:
			mPager.setCurrentItem(1);
			break;
		case R.id.bt_rechange:
			mPager.setCurrentItem(0);
			break;
		case R.id.bt_user_info:
			OrderFragment.this.getActivity().onBackPressed();
			break;
		}
	}


	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					bt_practice.setTextColor(resources.getColor(R.color.bt_background_unselected));
				}
				bt_rechang.setTextColor(resources.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					bt_rechang.setTextColor(resources
							.getColor(R.color.bt_background_unselected));
				}
				bt_practice.setTextColor(resources.getColor(R.color.bt_background_selected));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(200);
			iv_line.startAnimation(animation);
		}
	}
}
