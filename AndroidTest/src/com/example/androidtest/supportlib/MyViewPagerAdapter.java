package com.example.androidtest.supportlib;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

	private String[] titles;
	private List<Fragment> fragments;
	
	public MyViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
		super(fm);
		this.titles = titles;
		this.fragments = fragments;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}
	
	@Override
	public Fragment getItem(int index) {
		return fragments.get(index);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
