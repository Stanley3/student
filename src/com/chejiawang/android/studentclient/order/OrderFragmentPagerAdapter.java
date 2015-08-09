package com.chejiawang.android.studentclient.order;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class OrderFragmentPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> fragmentList;
	public OrderFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public OrderFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}
	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
//
//	@Override
//	public int getItemPosition(Object object) {
//		return PagerAdapter.POSITION_NONE;
//	}
}
