package com.chejiawang.android.studentclient.home;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.bean.SchoolInfo;
import com.chejiawang.android.studentclient.utils.UIHelper;

/**
 * 百度地图
 * 
 * @author 孙晓雨
 *
 */
public class HomeInFragment1 extends Fragment{
	private String TAG = "HomeInFragment1";
	// 地图控件
	MapView mapView = null;
	// 地图对象
	public BaiduMap baiduMap = null;
	//private App appContext;
	// 弹出窗口视图
	View infowindow = null;
	// 弹出窗口的文字
	TextView popText = null;
	// 弹出窗口图层
	private InfoWindow infoWindowOverlay = null;
	// 是否手动触发请求定位
	private boolean isRequest = false;
	// 定位核心类
	public LocationClient locationClient = null;
	// 自定义图标
	BitmapDescriptor mCurrentMarker = null;
	boolean isFirstLoc = true;// 是否首次定位

	// 定义驾校的list集合
	private List<SchoolInfo> shool_list = new ArrayList<SchoolInfo>();

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// selectorDialog.cancel();
			switch (msg.what) {
			case 2:
				shool_list = (List<SchoolInfo>) msg.obj;

			case 3:
				UIHelper.ToastMessage(HomeInFragment1.this.getActivity(),
						"没有驾校数据");
			}
		};
	};

	public BDLocationListener myListener = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				return;

			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData); // 设置定位数据

			if (isFirstLoc || isRequest) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory
						.newLatLngZoom(ll, 16); // 设置地图中心点以及缩放级别
				showInfo(location);
				isRequest = false;

				isFirstLoc = false;

				baiduMap.animateMapStatus(u);
			}
		}
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_in_home1, container,
				false);
//		mapView = (MapView) view.findViewById(R.id.bm_mapView);
		infowindow = inflater.inflate(R.layout.infowindow_layout, null);
		popText = (TextView) infowindow.findViewById(R.id.location_tips);

//		baiduMap = mapView.getMap();
//		// 开启定位图层
//		baiduMap.setMyLocationEnabled(true);
//		baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//		baiduMap.setMaxAndMinZoomLevel(3, 16);
//		locationClient = new LocationClient(this.getActivity()
//				.getApplicationContext()); // 实例化LocationClient类
//		locationClient.registerLocationListener(myListener); // 注册监听函数
//		this.setLocationOption(); // 设置定位参数
//		locationClient.start();
//
//		// 点击按钮手动请求定位
//		((Button) view.findViewById(R.id.request))
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						requestLocation();
//					}
//				});
//
//		// 定义Maker坐标点 //此处需要重写， 传入list的坐标信息
//		LatLng point = new LatLng(39.963175, 116.400244);
//		// 构建Marker图标
//		BitmapDescriptor bitmap = BitmapDescriptorFactory
//				.fromResource(R.drawable.location_arrows);
//		// 构建MarkerOption，用于在地图上添加Marker
//		OverlayOptions option = new MarkerOptions().position(point)
//				.icon(bitmap);
//		// 在地图上添加Marker，并显示
//		baiduMap.addOverlay(option);
		//
		return view;
	}

	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开GPS
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

		locationClient.setLocOption(option);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logger.e(TAG, "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logger.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		Logger.e(TAG, "onDestroy");
//		// 退出时销毁定位
//		locationClient.stop();
//		baiduMap.setMyLocationEnabled(false);
//		// TODO Auto-generated method stub
		super.onDestroy();
//		mapView.onDestroy();
//		mapView = null;
	}

	@Override
	public void onDestroyView() {
		Logger.e(TAG, "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		Logger.e(TAG, "onDetach");
		super.onDetach();
	}

	@Override
	public void onPause() {
		Logger.e(TAG, "onPause");
		super.onPause();
//		mapView.onPause();
	}

	@Override
	public void onResume() {
		Logger.e(TAG, "onResume");
		super.onResume();
//		mapView.onResume();
	}
	/**
	 * 显示弹出窗口图层PopupOverlay
	 * 
	 * @param location
	 */
	private void showInfo(BDLocation location) {
		popText.setText("[我的位置]\n" + location.getAddrStr());
		infoWindowOverlay = new InfoWindow(infowindow, new LatLng(
				location.getLatitude(), location.getLongitude()), 15);
		baiduMap.showInfoWindow(infoWindowOverlay);
	}

	/**
	 * 手动请求定位的方法
	 */
	public void requestLocation() {
		isRequest = true;
		if (locationClient != null && locationClient.isStarted()) {
			UIHelper.ToastMessage(HomeInFragment1.this.getActivity(),
					"正在定位......");
			locationClient.requestLocation();
		} else {
			// Log.d("log", "locClient is null or not started");
		}
	}

}
