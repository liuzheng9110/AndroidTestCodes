package com.example.androidtest.sliding.residemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.androidtest.R;

public class HomeFragment extends Fragment {
	
	private View parentView;
	private ResideMenu resideMenu;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		parentView = inflater.inflate(R.layout.residemenu_home_fragment_layout, container, false);
		
		setResideMenu();
		
		return parentView;
	}

	private void setResideMenu() {
		SlidingResideMenuAct act = (SlidingResideMenuAct) getActivity();
		resideMenu = act.getResideMenu();
		
		// 点击展开菜单
		parentView.findViewById(R.id.click_to_open).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		
	}
}
