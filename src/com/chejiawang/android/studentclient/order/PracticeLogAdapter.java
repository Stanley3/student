package com.chejiawang.android.studentclient.order;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.MyOrderRecord;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PracticeLogAdapter extends RecycleBaseAdapter {

	public PracticeLogAdapter() {
		super();
	}
	public PracticeLogAdapter(View headerView){
		mHeaderView = headerView;
	}
	
	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.have_order_list_item, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}
	
	@Override
	protected void onBindItemViewHolder(RecycleBaseAdapter.ViewHolder vh, int position) {
		super.onBindItemViewHolder(vh, position);
		PracticeLogAdapter.ViewHolder holder= (ViewHolder) vh;
		MyOrderRecord record = (MyOrderRecord) _data.get(position);
		
//		holder.tv_name.setText(record.getStudent_name());
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
		
		public ImageView iv_coach_phone;
		public TextView tv_coach_name;
		public TextView tv_order_day;
		public TextView tv_course_status;
		public TextView tv_order_time;
		public TextView tv_reach_before;
		public ImageView iv_online_status;
		
		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			iv_coach_phone = (ImageView) view.findViewById(R.id.iv_coach_photo);
			tv_coach_name = (TextView) view.findViewById(R.id.tv_coach_name);
			tv_order_day = (TextView) view.findViewById(R.id.tv_order_day);
			tv_course_status = (TextView) view.findViewById(R.id.tv_course_status);
			tv_order_time = (TextView) view.findViewById(R.id.tv_order_time);
			tv_reach_before = (TextView) view.findViewById(R.id.tv_reach_before);
			iv_online_status = (ImageView) view.findViewById(R.id.iv_online_status);
		}
	}

}
