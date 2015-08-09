package com.chejiawang.android.studentclient.home;


import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.MyOrderRecord;
import com.chejiawang.android.studentclient.bean.SchoolInfoBasedDistance;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DistanceRecycleAdapter extends RecycleBaseAdapter {

	public DistanceRecycleAdapter() {
		super();
	}
	public DistanceRecycleAdapter(View headerView){
		mHeaderView = headerView;
	}
	
	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.school_info_list_view, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}
	
	@Override
	protected void onBindItemViewHolder(RecycleBaseAdapter.ViewHolder vh, int position) {
		super.onBindItemViewHolder(vh, position);
		DistanceRecycleAdapter.ViewHolder holder= (ViewHolder) vh;
		SchoolInfoBasedDistance record = (SchoolInfoBasedDistance) _data.get(position);
		
		holder.tv_school_name.setText(record.getSchool_name());
//		holder.tv_coach_num.setText(record.get);
		holder.tv_distance_me.setText((record.getDistance().intValue()) + "");
//		String course = record.getCourse_status();
//		course = course.equals("2")? "预约科目二" : "预约科目三";
//		holder.tv_order_subject.setText(course);
//		String[] start_time = record.getTraining_start_time().split(" ");
//		String[] end_time = record.getTraining_end_time().split(" ");
//		holder.tv_order_day.setText("日期:" + start_time[0]);
//		holder.tv_practice_time.setText(start_time[1] + "-" + end_time[1]);
		
		//需要添加是否是VIP包过信息    2015.6.27  孙晓雨
		
		
	}
	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder{
		
		public TextView tv_school_name;
		public TextView tv_subject;
		
		public TextView tv_coach_num;
		public TextView tv_car_num;
		public TextView tv_distance_me;
		
		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			
			tv_school_name = (TextView) view.findViewById(R.id.tv_school_name);
			tv_subject = (TextView) view.findViewById(R.id.tv_subject);
			tv_coach_num = (TextView) view.findViewById(R.id.tv_coach_num);
			tv_car_num = (TextView) view.findViewById(R.id.tv_car_num);
			tv_distance_me = (TextView) view.findViewById(R.id.tv_distance_me);
		}
	}

}
