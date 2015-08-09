package com.chejiawang.android.studentclient.finance;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> mArray;

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mArray) {
		super(fm);
		this.mArray = mArray;
	}
	@Override
	public Fragment getItem(int arg0) {
		return mArray.get(arg0);
	}

	@Override
	public int getCount() {
		return mArray.size();
	}

}
