package com.chejiawang.android.studentclient;

import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.finance.FinanceFragment;
import com.chejiawang.android.studentclient.home.HomeFragment;
import com.chejiawang.android.studentclient.order.OrderFragment;
import com.chejiawang.android.studentclient.statistics.StatisticsFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;
/**
 * 导航页的实现
 * @author sunxiaoyu
 *
 */
public class MainGuideActivity extends FragmentActivity implements OnClickListener{
	String TAG = "MainGuideActivity";

	private  FragmentManager fMgr;
	/**
	 * 底部四个导航按钮
	 */
	private RadioButton rb_home;
	private RadioButton rb_order;
	private RadioButton rb_finance;
	private RadioButton rb_statistics;
	/**预约Fragment**/
	Fragment orderFragment;
	/**我的财务Fragment**/
	Fragment financeFragment;
	/** 统计Fragment**/
	Fragment statisticsFragment;
	/**首页Fragment**/
	Fragment homeFragment;
	
	//CoachFragment coachFragment;
	//public SchoolInfoReceiver receiver;
	/**
	 * 退出开始时间
	 */
	private long exitStartTime = System.currentTimeMillis();
//	public Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			switch(msg.what){
//			case 1://发送消息给HomeFragment
//				homeFragment.handler.handleMessage(msg);	
//			}
//		}
//	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mian_guid);
		fMgr = getSupportFragmentManager();
		initView();
		initFragment();
		initAction();
		
		Logger.e(TAG, "onCreate!");
		//registerMessageReceiver();
		
		
	}
	public void initAction() {
		rb_home.setOnClickListener(this);
		rb_order.setOnClickListener(this);
		rb_finance.setOnClickListener(this);
		rb_statistics.setOnClickListener(this);
	}
	/**
	 * 初始化所有视图
	 */
	public void initView() {
		rb_home = (RadioButton) findViewById(R.id.rb_home);
		rb_order = (RadioButton) findViewById(R.id.rb_order);
		rb_finance = (RadioButton) findViewById(R.id.rb_finance);
		rb_statistics = (RadioButton) findViewById(R.id.rb_statistics);
	}
	public void onClick(View v) {
		popAllRadioButton();
		((RadioButton)v).setChecked(true);
		int id = v.getId();
		FragmentTransaction ft = fMgr.beginTransaction();
		switch(id){
		case R.id.rb_home:
			homeFragment = new HomeFragment();
			ft.replace(R.id.fragmentRoot, homeFragment, "Home");
			ft.commit();
			break;
		case R.id.rb_order:
			orderFragment = new OrderFragment();
			ft.replace(R.id.fragmentRoot, orderFragment, "Order");
			ft.commit();
			break;
		case R.id.rb_finance:
			financeFragment = new FinanceFragment();
			ft.replace(R.id.fragmentRoot, financeFragment, "Finance");
			ft.commit();
			break;
		case R.id.rb_statistics:
			statisticsFragment = new StatisticsFragment();
			ft.replace(R.id.fragmentRoot, statisticsFragment, "Statistics");
			ft.commit();
			break;
		}
	}
	/**
	 * 初始化所有Fragment
	 */
	private void initFragment() {
		FragmentTransaction ft = fMgr.beginTransaction();
		homeFragment = new HomeFragment();
		ft.replace(R.id.fragmentRoot, homeFragment, "Home");
		ft.commit();
		rb_home.setChecked(true);
	}
	/**
	 * 弹起所有按钮，方便管理
	 */
	public void popAllRadioButton(){
		rb_home.setChecked(false);
		rb_order.setChecked(false);
		rb_finance.setChecked(false);
		rb_statistics.setChecked(false);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		long exitFinishTime = System.currentTimeMillis();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (exitFinishTime - exitStartTime > 2000) {
				Toast.makeText(this, "再按一次返回键关闭程序", Toast.LENGTH_SHORT).show();
				;
				exitStartTime = exitFinishTime;
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
//	/**
//	 * 实现Home2Fragment的接口并和其通信
//	 */
//	@Override
//	public void OnSendMyMessage(Message msg) {
//		this.handler.sendMessage(msg);
//		
//	}
//	public class SchoolInfoReceiver extends BroadcastReceiver {
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				//Logger.e(TAG, "我接收到了广播");
//				Bundle bundle = intent.getBundleExtra("com.scu.studentclient.schoolInfo");
//				FragmentTransaction ft = fMgr.beginTransaction();
//				popAllFragmentsExceptTheBottomOne();
//				ft.hide(fMgr.findFragmentByTag("Home"));
//				coachFragment = new CoachFragment();
//				coachFragment.setArguments(bundle);
//				ft.add(R.id.fragmentRoot, coachFragment, "CoachInfo");
//				ft.addToBackStack("CoachInfo");
//				ft.commit();
//			}
//
//	}
//	 /**
//	   * 动态注册广播
//	   */
//	public void registerMessageReceiver() {
//		receiver = new SchoolInfoReceiver();
//	    IntentFilter filter = new IntentFilter();
//	    filter.addAction(ConstantValue.ACTION_INTENT_GUID);
//	    registerReceiver(receiver, filter);
//	  }
	
}
