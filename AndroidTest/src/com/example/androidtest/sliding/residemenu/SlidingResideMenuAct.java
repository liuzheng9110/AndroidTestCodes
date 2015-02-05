package com.example.androidtest.sliding.residemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

/**
 * 
 * @author Administrator
 *
 */
public class SlidingResideMenuAct extends FragmentActivity implements OnClickListener{
	
	private SlidingResideMenuAct mContext;
	private TextView titleTv;
	private ResideMenu resideMenu;
	
	private ResideMenuItem homeItem, profileItem, calendarItem, settingsItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sliding_residemenu_layout);
		mContext = this;
		
		titleTv = (TextView) findViewById(R.id.title_textview);
		
		initResideMenuView();
		
		if( savedInstanceState == null )
            changeFragment(new HomeFragment());
	}
	
	private void initResideMenuView() {
		// 左侧菜单
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		
		homeItem = new ResideMenuItem(this, R.drawable.icon_home, "Home");
		profileItem = new ResideMenuItem(this, R.drawable.icon_profile, "Profile");
		calendarItem = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
		settingsItem = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
		
		homeItem.setOnClickListener(this);
		profileItem.setOnClickListener(this);
		calendarItem.setOnClickListener(this);
		settingsItem.setOnClickListener(this);
		
		resideMenu.addMenuItem(homeItem, ResideMenu.DIRECTION_LEFT); 
		resideMenu.addMenuItem(profileItem, ResideMenu.DIRECTION_LEFT); 
		resideMenu.addMenuItem(calendarItem, ResideMenu.DIRECTION_LEFT); 
		resideMenu.addMenuItem(settingsItem, ResideMenu.DIRECTION_LEFT); 
		
		// 禁止哪个方向
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		
		// 顶部title 按钮
		findViewById(R.id.title_left_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		
		findViewById(R.id.title_right_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		if (v == homeItem) {
			titleTv.setText("Home");
			changeFragment(new HomeFragment());
			Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
		}else if (v == profileItem) {
			titleTv.setText("Profile");
			changeFragment(new ProfileFragment());
			Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
		}else if (v == calendarItem) {
			titleTv.setText("Calendar");
			changeFragment(new CalendarFragment());
			Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show();
		}else if (v == settingsItem) {
			titleTv.setText("Settings");
			changeFragment(new SettingsFragment());
			Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
		}
		resideMenu.closeMenu();
	}
	
	private void changeFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, fragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}
	
	public ResideMenu getResideMenu() {
		return resideMenu;
	}
}
