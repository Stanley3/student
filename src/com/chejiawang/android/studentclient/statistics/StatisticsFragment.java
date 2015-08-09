package com.chejiawang.android.studentclient.statistics;




import com.chejiawang.android.studentclient.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 统计页面的Fragment
 * @author sunxiaoyu
 *
 */
public class StatisticsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_statistics, container, false);
	}
}
