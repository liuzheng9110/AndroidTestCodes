package com.example.androidtest.sliding;

import com.example.androidtest.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 
 * @author liuzheng
 * https://github.com/liuzheng9110/SlidingMenu
 * 
 * sliding_content_lay 	 默认显示的  content 布局
 * sliding_menu_lay		滑动出的 menu 布局
 * 
 */

public class SlidingMenuAct extends FragmentActivity {
	
	private FragmentManager fManager;
	private FragmentTransaction transaction;

	private SlidingMenu slidingMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setTitle("SlidingMenu");
		
		setContentView(R.layout.sliding_content_lay);
		
		fManager = getSupportFragmentManager();
		transaction = fManager.beginTransaction();
		transaction.replace(R.id.sliding_content_lay, new SlidingMenuContentFragment("SlidingMenu", "SlidingMenu", SlidingAct.TYPE_SLIDING_MENU)).commit();
		
		// 设置滑动菜单的属性值
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
		slidingMenu.setShadowWidth(10);		// 设置阴影宽度
		slidingMenu.setFadeDegree(0.35f);	// 动画效果
		slidingMenu.setBehindOffset(100);	// 设置 menu 和 content 重叠宽度  单位px
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.sliding_menu_lay);// 设置 menu 布局
		
		// 设置 fragment
		transaction = fManager.beginTransaction();
		transaction.replace(R.id.sliding_menu_lay, new SlidingMenuListFragment()).commit();
		
	}
	
	private void openMenu(){
		slidingMenu.showMenu();
	}

	private void closeMenu(){
		slidingMenu.showContent();
	}
	
	public void switchMenu(){
		if (slidingMenu.isMenuShowing()) {
			closeMenu();
		}else {
			openMenu();
		}
	}
	
	public void switchContent(Fragment fragment){
		transaction = fManager.beginTransaction();
		transaction.replace(R.id.sliding_content_lay, fragment).commit();
		// 
		switchMenu();
	}
	
	
	@Override
	public void onBackPressed() {
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		}else{
			super.onBackPressed();
		}
	}
}
