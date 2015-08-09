package com.chejiawang.android.studentclient.home.coach;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.order.HaveOrderRecycleAdapter.ViewHolder;

public class CoachOrderAdapter extends RecycleBaseAdapter{
	
	

	public CoachOrderAdapter() {
		super();
	}


	public CoachOrderAdapter(View headerView) {
		mHeaderView = headerView;
	}
	
	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.coach_select_list_item, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}
	
	@Override
	protected void onBindItemViewHolder(
			com.chejiawang.android.studentclient.base.RecycleBaseAdapter.ViewHolder vh,
			int position) {
		
	}
	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder{
		
		public ImageView iv_coach_photo;
		public TextView tv_coach_name;
		public TextView tv_order_times;
		public TextView tv_course_status;
		public TextView tv_edu_times;
		public TextView tv_edu_age;
		
		public ImageView iv_star1;
		public ImageView iv_star2;
		public ImageView iv_star3;
		public ImageView iv_star4;
		public ImageView iv_star5;
		
		public ImageView iv_online_status;
		
		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			iv_coach_photo = (ImageView) view.findViewById(R.id.iv_coach_photo);
			tv_coach_name = (TextView) view.findViewById(R.id.tv_coach_name);
			tv_order_times = (TextView) view.findViewById(R.id.tv_order_times);
			tv_course_status = (TextView) view.findViewById(R.id.tv_course_status);
			tv_edu_times = (TextView) view.findViewById(R.id.tv_edu_times);
			tv_edu_age = (TextView) view.findViewById(R.id.tv_edu_age);
			
			iv_star1 = (ImageView) view.findViewById(R.id.iv_star1);
			iv_star2 = (ImageView) view.findViewById(R.id.iv_star2);
			iv_star3 = (ImageView) view.findViewById(R.id.iv_star3);
			iv_star4 = (ImageView) view.findViewById(R.id.iv_star4);
			iv_star5 = (ImageView) view.findViewById(R.id.iv_star5);
			
			
			iv_online_status = (ImageView) view.findViewById(R.id.iv_online_status);
		}
	}

}
