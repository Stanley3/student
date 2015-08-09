package com.chejiawang.android.studentclient.finance;

import java.util.ArrayList;




import com.chejiawang.android.studentclient.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class FinanceFragment extends Fragment implements OnClickListener{
	/**
	 * 用户信息
	 */
	private Button bt_user_info;
	/**
	 * 消费记录
	 */
	private Button bt_cost_log;
	/**
	 * 充值记录
	 */
	private Button bt_rechange_log;

	/**
	 * 存放两个fragment的容器
	 */
	private ViewPager vp_finance;
	/**
	 * 按钮下切换的下划线
	 */
	private ImageView iv_line;
	/**
	 * 存放需要切换的三个Fragment
	 */
	private ArrayList<Fragment> mArray;

//	private Fragment costLog;
	
	private Fragment rechangeLog;

	/**
	 * 当前第一几个按钮被选中
	 */
	int currentIndex = 0;
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
	private Resources resource;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_finance, container, false);
		resource = this.getResources();
		initView(view);
		initWidth();
		initFragment();
		initAction();
		bt_cost_log.setTextColor(resource.getColor(R.color.bt_background_selected));
		return view;
	}
	private void initAction() {
		bt_user_info.setOnClickListener(this);
		bt_cost_log.setOnClickListener(this);
		bt_rechange_log.setOnClickListener(this);
		vp_finance.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mArray));
		vp_finance.setOnPageChangeListener(new MyPagerChangerListener());
		vp_finance.setOffscreenPageLimit(mArray.size());//设置初始预览界面的个数
		vp_finance.setCurrentItem(0);
		
	}
	private void initWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        lineWidth = screenW / buttonNum;
		
	}
	private void initFragment() {
		mArray = new ArrayList<Fragment>();
//		costLog = new FragmentInFinance1();
		rechangeLog = new FragmentInFinance2();
//		mArray.add(costLog);
		mArray.add(rechangeLog);
		
	}
	private void initView(View view) {
		bt_user_info = (Button) view.findViewById(R.id.bt_user_info);
		bt_cost_log = (Button) view.findViewById(R.id.bt_cost_log);
		bt_rechange_log = (Button) view.findViewById(R.id.bt_rechange_log);
		vp_finance = (ViewPager) view.findViewById(R.id.vp_finance);
		iv_line = (ImageView) view.findViewById(R.id.iv_line);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
//		case R.id.bt_cost_log:
//			vp_finance.setCurrentItem(0);
//			break;
//		case R.id.bt_rechange_log:
//			vp_finance.setCurrentItem(1);
//			break;
		case R.id.bt_user_info:
			//UIHelper.startActivityUtil(this.getActivity(), UserInfoActivity.class);
			break;
		}
	}
	class MyPagerChangerListener implements OnPageChangeListener {

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
			switch(arg0){
			case 0:
				if(currentIndex == 1){
					animation = new TranslateAnimation(lineWidth, 0, 0, 0);
					bt_rechange_log.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_cost_log.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if(currentIndex == 0){
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					bt_cost_log.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_rechange_log.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(200);
			iv_line.startAnimation(animation);
		}
	}
}
