package com.example.androidtest.supportlib;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.androidtest.R;

/**
 * 
 *  Class Name: SupportLibActivity.java
 *  Function: Android 兼容库学习
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月16日 上午10:20:31
 *  @Copyright http://liuz.me 
 *  @url
 */
public class SupportLibActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnClickListener {
	
	private DrawerLayout drawerLayout;  // 最外层包裹布局
	private CoordinatorLayout coordinatorLayout; // 整体布局 包裹 AppBar ToolBar ContentLayout 
	private AppBarLayout appBarLayout; 
	private Toolbar toolbar; // 顶部 Actionbar 
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private FloatingActionButton fActionButton;
	private NavigationView navigationView;
	
	// 
	private String[] mTitles;
	private List<Fragment> fragments;
	private MyViewPagerAdapter vpAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("SupportLibActivity");
		setContentView(R.layout.support_lib_layout);
		
		initView();
		
		initDatas();
		//
		setViewData();
		
	}

	/**
	 * 
	 *  Function: 控件数据设置 
	 *  @author liuzheng
	 *  @created 2015年7月17日 上午10:42:53
	 */
	private void setViewData() {
		// 设置 ToolBar 显示
		setSupportActionBar(toolbar);
		
		// 初始化 drawablelayout 开关显示，即最左侧 icon 
		/**
		 * Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes
		 */
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
		mDrawerToggle.syncState();
		drawerLayout.setDrawerListener(mDrawerToggle); // 设置draw打开关闭监听
		
		// 侧滑顶部信息显示，也可在xml中使用app:headerLayout="@layout/header_nav"来设置
		navigationView.inflateHeaderView(R.layout.support_lib_head_nav_layout);
		// 侧滑菜单组显示，也可在xml中使用app:menu="@menu/menu_nav"来设置
		navigationView.inflateMenu(R.menu.support_lib_nav_menu);
		// 侧滑菜单点击效果
		navigationView.setNavigationItemSelectedListener(onNavItemSelLisn);
		
		// 初始化viewpager适配器
		vpAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
		viewPager.setAdapter(vpAdapter);
		// 设置viewpager最大缓存数
		viewPager.setOffscreenPageLimit(5);
		// 
		viewPager.addOnPageChangeListener(null);
		// 设置TableLayout模式
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		// 将TableLayout和ViewPager关联起来，使两者联动
		tabLayout.setupWithViewPager(viewPager);
		// 设置tabs标题来源为 viewpager adapter 
		tabLayout.setTabsFromPagerAdapter(vpAdapter);
		
		// 设置FloatingActionButton 点击事件
		fActionButton.setOnClickListener(this);
	}

	/**
	 * 侧滑菜单点击效果
	 */
	private OnNavigationItemSelectedListener onNavItemSelLisn = new OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {
			String msg = "";
			
			switch (menuItem.getItemId()) {
			case R.id.supprto_lib_menu_home:
				msg = getString(R.string.home);
				break;
			case R.id.supprto_lib_menu_catagories:
				msg = getString(R.string.catagories);
				break;
			case R.id.supprto_lib_menu_feedback:
				msg = getString(R.string.feedback);
				break;
			case R.id.supprto_lib_menu_setting:
				msg = getString(R.string.settings);
				break;
			}
			
			// 设置侧滑菜单选中，
			menuItem.setChecked(true);
			drawerLayout.closeDrawers();;
			
			// android support design类 Toast 显示
			SnackbarUtil.show(viewPager, msg, 0);
			
			return true;
		}
	};
	
	/**
	 * 
	 *  Function: 初始化模拟数据
	 *  @author liuzheng
	 *  @created 2015年7月17日 上午10:42:03
	 */
	private void initDatas() {
		mTitles = getResources().getStringArray(R.array.tab_titles);
		//
		fragments = new ArrayList<Fragment>();
		for (int i = 0; i < mTitles.length; i++) {
			Bundle bundle = new Bundle();
			bundle.putInt("flag", i);
			
			MyFragment fragment = new MyFragment();
			fragment.setArguments(bundle);
			fragments.add(fragment);
		}
	}

	/**
	 * 
	 *  Function: 初始化view
	 *  @author liuzheng
	 *  @created 2015年7月17日 上午10:41:51
	 */
	private void initView() {
		drawerLayout = (DrawerLayout) findViewById(R.id.support_lib_drawer_lay);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.support_lib_coordinat_layout);
		appBarLayout = (AppBarLayout) findViewById(R.id.support_lib_appbar_layout);
		toolbar = (Toolbar) findViewById(R.id.support_lib_toolbar);
		tabLayout = (TabLayout) findViewById(R.id.support_lib_tab_layout);
		viewPager = (ViewPager) findViewById(R.id.support_lib_viewpager);
		fActionButton = (FloatingActionButton) findViewById(R.id.support_lib_float_act_btn);
		navigationView = (NavigationView) findViewById(R.id.support_lib_navgation_view);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.support_lib_float_act_btn:
			SnackbarUtil.show(v, "+1", 0);
			break;
		default:
			break;
		}
	}
	 
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// 设置toolbar 标题
		toolbar.setTitle(mTitles[arg0]);
	}
}
