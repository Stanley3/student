package com.chejiawang.android.studentclient.home;

import java.util.ArrayList;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> fragmentList;
	public HomeFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	public HomeFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
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
