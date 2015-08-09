package com.chejiawang.android.studentclient.home;

import java.util.ArrayList;
import java.util.List;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.bean.SchoolInfo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment extends Fragment implements OnClickListener {
	private String TAG = "HomeFragment";
	/**
	 * Fragment容器
	 */
	private MapViewPager mPager;
	/**
	 * 按地图查找按钮
	 */
	private Button bt_map;
	/**
	 * 按距离查找按钮
	 */
	private Button bt_distance;
	/**
	 * 用户信息按钮
	 */
	private Button bt_user_info;
	/**
	 * 按钮下切换的下划线
	 */
	private ImageView iv_line;
	/**
	 * ViewPager中的两个Fragment
	 */
	Fragment home1, home2;
	/**
	 * 资源按钮
	 */
	Resources resource;
	/**
	 * 全局application
	 */
	//private App appContext;
	/**
	 * 当前选中的按钮
	 */
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
	 * /** 是否更新标志位，为1时候重新下载，为2时候先查找缓存中的数据，缓存如果存在的话直接读取，不存在的话再下载
	 */
	private int downloadMask = 1;
	/**
	 * 定义驾校的list集合
	 */
	private List<SchoolInfo> school_list = new ArrayList<SchoolInfo>();
	/**
	 * Fragment容器
	 */
	private ArrayList<Fragment> fragmentsList;

//	public Handler handler = new Handler() {
//		public void handleMessage(Message msg) {
//			// selectorDialog.cancel();
//			switch (msg.what) {
//			case 1:
//				// shool_list = (List<SchoolInfo>) msg.obj;
//				int redownloadMask = 1;
//				getInfoList(redownloadMask);
//			case 2:
//				// school_list = (List<SchoolInfo>) msg.obj;
//				refreshAllListView(msg);
//
//			case 3:
//				Logger.e(TAG, "我先发");
//				refreshAllListView(msg);
//				// UIHelper.ToastMessage(HomeFragment.this.getActivity(),
//				// "没有驾校数据");
//			}
//		};
//	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		resource = getResources();
		initView(view);
		initWidth();
		initFragment();
		InitAction();
		bt_map.setTextColor(resource.getColor(R.color.bt_background_selected));
		return view;
	}

	/**
	 * 初始化线宽
	 */
	private void initWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		lineWidth = screenW / buttonNum;

	}

	/**
	 * 初始化所有时间的监听按钮
	 */
	private void InitAction() {

		bt_map.setOnClickListener(this);
		bt_distance.setOnClickListener(this);
		bt_user_info.setOnClickListener(this);
		
        /**
         * 关闭预加载，保持Fragment一次只加载一个
         */
		mPager.setOffscreenPageLimit(1);
		mPager.setAdapter(new HomeFragmentPagerAdapter(
				getChildFragmentManager(), fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
//		mPager.setCurrentItem(0);
		mPager.setCurrentItem(1);
	}

	/**
	 * 初始化Fragment
	 */
	public void initFragment() {
		// 去掉了地图页面
		fragmentsList = new ArrayList<Fragment>();
//		home1 = new HomeInFragment1();
		home2 = new HomeInFragment2();
//		fragmentsList.add(home1);
		fragmentsList.add(home2);
	}

	/**
	 * 初始化所有的控件
	 * 
	 * @param parentView
	 */
	private void initView(View parentView) {
		bt_map = (Button) parentView.findViewById(R.id.bt_map);
		bt_distance = (Button) parentView.findViewById(R.id.bt_distance);
		bt_user_info = (Button) parentView.findViewById(R.id.bt_user_info);
		iv_line = (ImageView) parentView.findViewById(R.id.iv_line);
		mPager = (MapViewPager) parentView.findViewById(R.id.vp_select);

	}

	/**
	 * 按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_distance:
			mPager.setCurrentItem(1);
			break;
//		case R.id.bt_map:
//			mPager.setCurrentItem(0);
//			break;
		case R.id.bt_user_info:
			break;

		}
	}

	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					bt_distance.setTextColor(resource
							.getColor(R.color.bt_background_unselected));
				}
				bt_map.setTextColor(resource
						.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					bt_map.setTextColor(resource
							.getColor(R.color.bt_background_unselected));
				}
				bt_distance.setTextColor(resource
						.getColor(R.color.bt_background_selected));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(200);
			iv_line.startAnimation(animation);

		}

	}
}
