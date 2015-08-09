package com.chejiawang.android.studentclient.finance;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.base.RecycleBaseAdapter;
import com.chejiawang.android.studentclient.bean.DisplayStudentDepositReocrd;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CostLogAdapter extends RecycleBaseAdapter {

	public CostLogAdapter() {
		super();
	}

	public CostLogAdapter(View headerView) {
		mHeaderView = headerView;
	}

	@Override
	protected View OnCreateItemView(ViewGroup parent, int viewType) {
		return getLayoutInflater(parent.getContext()).inflate(R.layout.item_charge_log, null);
	}

	@Override
	protected ViewHolder onCreateItemViewHolder(View view, int viewType) {
		return new ViewHolder(viewType, view);
	}

	@Override
	protected void onBindItemViewHolder(RecycleBaseAdapter.ViewHolder vh, int position) {
		super.onBindItemViewHolder(vh, position);
		CostLogAdapter.ViewHolder holder = (ViewHolder) vh;
		DisplayStudentDepositReocrd record = (DisplayStudentDepositReocrd) _data.get(position);

		holder.tv_date.setText(record.getDeposit_date());
		holder.tv_charge_address.setText(record.getDeposit_address());
		// holder.tv_subject_num.setText("");

		// 需要添加是否是VIP包过信息 2015.6.27 孙晓雨

	}

	public static class ViewHolder extends RecycleBaseAdapter.ViewHolder {

		public TextView tv_date;
		public TextView tv_charge_address; // 充值地址
		public TextView tv_coach_name;
		public TextView tv_course_subject; // 科目
		public TextView tv_practise_hour; // 练习时长

		public ViewHolder(int viewType, View view) {
			super(viewType, view);
			tv_date = (TextView) view.findViewById(R.id.tv_date);
			tv_charge_address = (TextView) view.findViewById(R.id.tv_charge_address);
			tv_course_subject = (TextView) view.findViewById(R.id.tv_course_subject);
			tv_practise_hour = (TextView) view.findViewById(R.id.tv_practise_hour);
		}
	}

}
