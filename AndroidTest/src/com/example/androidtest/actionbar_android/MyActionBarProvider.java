package com.example.androidtest.actionbar_android;

import com.example.androidtest.R;

import android.content.Context;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class MyActionBarProvider extends android.view.ActionProvider {

	public MyActionBarProvider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		subMenu.clear();
		subMenu.add("sub item 1").setIcon(R.drawable.ic_launcher_00)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return true;
					}
				});
		subMenu.add("sub item 2").setIcon(R.drawable.ic_launcher_03)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
	}

	@Override
	public boolean hasSubMenu() {
		// TODO Auto-generated method stub
		return true;
	}

}
