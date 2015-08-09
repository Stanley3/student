package com.chejiawang.android.studentclient.home.coach;

import java.util.ArrayList;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.app.AppContext;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CoachActivity extends FragmentActivity implements OnClickListener {
	private String TAG = "CoachActivity";
	/**
	 * 教练list
	 */
	private ViewPager vp_coach_info;
	/**
	 * 返回按钮
	 */
	private Button bt_back;
	/**
	 * 学校名字
	 */
	private TextView tv_school_name;
	/**
	 * 有没有 专用设备，若有科目二专用设备，显示科目而专用设备，若没有，显示无。
	 */
	private TextView tv_subject;
	/**
	 * 学校的练习车的数量
	 */
	private TextView tv_car_num;
	/**
	 * 学校的教练数量
	 */
	private TextView tv_coach_num;
	/**
	 * 距离我目前多少米
	 */
	private TextView tv_distance_me;
	/**
	 * 学校地址
	 */
	private TextView tv_school_address;
	/**
	 * 综合排序按钮
	 */
	private Button bt_compre_rating;
	/**
	 * 人气优先排序按钮
	 */
	private Button bt_popu_rating;
	/**
	 * 常用教练按钮
	 */
	private Button bt_times_rating;
	/**
	 * 代表应用程序
	 */
	private AppContext appContext;
	/**
	 * 父类的activity
	 */
	private Context context;
	/**
	 * 标志那个按钮被点击 0：综合排序 1.人气优先 2. 常用教练
	 */
	private int bt_flag = 0;

	/**
	 * 学校名字
	 */
	private String school_name;
	/**
	 * 学校ID
	 */
	private String school_id;
	/**
	 * 学校地址
	 */
	private String school_address;
	/**
	 * 教练数量
	 */
	private String coach_number;
	/**
	 * 车辆数量
	 */
	private String vehicle_number;
	/**
	 * 科目二或科目三     2：科目二 3：科目三
	 */
	private Object subject_num;
	/**
	 * 按钮下切换的下划线
	 */
	private ImageView iv_line;
	/**
	 * 资源文件
	 */
	private Resources resource;
	/**
	 * 线宽
	 */
	int lineWidth = 0;
	/**
	 * 按钮的数量
	 */
	private int buttonNum = 3;
	/**
	 * 当前第一几个按钮被选中
	 */
	int currentIndex = 0;
	/**
	 * 存放需要切换的三个Fragment
	 */
	private ArrayList<Fragment> mArray;
	private Fragment comprohensiveFragment, populateFragment, timesFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_coach);
		appContext = (AppContext) this.getApplication();
		initView();
		initWidth();
		initFragment();
		initTextView();
		initAction();
		bt_compre_rating.setTextColor(resource.getColor(R.color.bt_background_selected));
	}
	private void initFragment() {
		mArray = new ArrayList<Fragment>();
		comprohensiveFragment = new CompreHensiveFragment();
		populateFragment = new PopulateFragment();
		timesFragment = new TimesFragment();
		mArray.add(comprohensiveFragment);
		mArray.add(populateFragment);
		mArray.add(timesFragment);
		
	}
	/**
	 * 初始化所有的点击事件
	 */
	public void initAction() {
		//tv_school_name.setBackgroundColor(getResources().getColor(R.color.my_blue));
		bt_compre_rating.setOnClickListener(this);
		bt_popu_rating.setOnClickListener(this);
		bt_times_rating.setOnClickListener(this);
		
		vp_coach_info.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mArray));
		vp_coach_info.setOnPageChangeListener(new MyPagerChangerListener());
		vp_coach_info.setOffscreenPageLimit(mArray.size());//设置初始预览界面的个数
		vp_coach_info.setCurrentItem(0);

	}

	private void initWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        lineWidth = screenW / buttonNum;
		
	}
	/**
	 * 初始化TextView中的数据
	 */
	private void initTextView() {
		tv_school_name.setText(school_name);
		tv_school_address.setText("地址:" + school_address);
		tv_car_num.setText("教练车:" + vehicle_number);
		tv_coach_num.setText("教练:" + coach_number);
		// tv_subject.setText("");
	}

	/**
	 * 初始化各个控件
	 * @param view
	 */
	private void initView() {
		vp_coach_info = (ViewPager) findViewById(R.id.lv_coach_info);
		bt_back = (Button)findViewById(R.id.bt_back);
		tv_school_address = (TextView) findViewById(R.id.tv_school_address);
		bt_compre_rating = (Button) findViewById(R.id.bt_compre_rating);
		bt_popu_rating = (Button) findViewById(R.id.bt_popu_rating);
		bt_times_rating = (Button) findViewById(R.id.bt_times_rating);
		
		tv_school_name = (TextView) findViewById(R.id.tv_school_name);
		tv_subject = (TextView) findViewById(R.id.tv_subject);
		tv_car_num = (TextView) findViewById(R.id.tv_car_num);
		tv_coach_num = (TextView) findViewById(R.id.tv_coach_num);
		tv_distance_me = (TextView) findViewById(R.id.tv_distance_me);
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_compre_rating:
			vp_coach_info.setCurrentItem(0);
			break;
		case R.id.bt_popu_rating:
			vp_coach_info.setCurrentItem(1);
			break;
		case R.id.bt_times_rating:
			vp_coach_info.setCurrentItem(2);
			break;
		case R.id.bt_back:
			this.onBackPressed();
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
					bt_popu_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 2){
					animation = new TranslateAnimation(2 * lineWidth, 0, 0, 0);
					bt_times_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_compre_rating.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			case 1:
				if(currentIndex == 0){
					animation = new TranslateAnimation(0, lineWidth, 0, 0);
					bt_compre_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 2){
					animation = new TranslateAnimation(2 * lineWidth, lineWidth, 0, 0);
					bt_times_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_popu_rating.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			case 2:
				if(currentIndex == 0 ){
					animation = new TranslateAnimation(0, 2 * lineWidth, 0, 0);
					bt_compre_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}else if(currentIndex == 1){
					animation = new TranslateAnimation(lineWidth, 2 * lineWidth, 0, 0);
					bt_popu_rating.setTextColor(resource.getColor(R.color.bt_background_unselected));
				}
				bt_times_rating.setTextColor(resource.getColor(R.color.bt_background_selected));
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(200);
			iv_line.startAnimation(animation);
		}
	}
}
