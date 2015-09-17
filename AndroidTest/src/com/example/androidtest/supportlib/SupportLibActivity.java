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
 *  Function: Android ���ݿ�ѧϰ
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015��7��16�� ����10:20:31
 *  @Copyright http://liuz.me 
 *  @url
 */
public class SupportLibActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnClickListener {
	
	private DrawerLayout drawerLayout;  // ������������
	private CoordinatorLayout coordinatorLayout; // ���岼�� ���� AppBar ToolBar ContentLayout 
	private AppBarLayout appBarLayout; 
	private Toolbar toolbar; // ���� Actionbar 
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
	 *  Function: �ؼ��������� 
	 *  @author liuzheng
	 *  @created 2015��7��17�� ����10:42:53
	 */
	private void setViewData() {
		// ���� ToolBar ��ʾ
		setSupportActionBar(toolbar);
		
		// ��ʼ�� drawablelayout ������ʾ��������� icon 
		/**
		 * Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes
		 */
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
		mDrawerToggle.syncState();
		drawerLayout.setDrawerListener(mDrawerToggle); // ����draw�򿪹رռ���
		
		// �໬������Ϣ��ʾ��Ҳ����xml��ʹ��app:headerLayout="@layout/header_nav"������
		navigationView.inflateHeaderView(R.layout.support_lib_head_nav_layout);
		// �໬�˵�����ʾ��Ҳ����xml��ʹ��app:menu="@menu/menu_nav"������
		navigationView.inflateMenu(R.menu.support_lib_nav_menu);
		// �໬�˵����Ч��
		navigationView.setNavigationItemSelectedListener(onNavItemSelLisn);
		
		// ��ʼ��viewpager������
		vpAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
		viewPager.setAdapter(vpAdapter);
		// ����viewpager��󻺴���
		viewPager.setOffscreenPageLimit(5);
		// 
		viewPager.addOnPageChangeListener(null);
		// ����TableLayoutģʽ
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		// ��TableLayout��ViewPager����������ʹ��������
		tabLayout.setupWithViewPager(viewPager);
		// ����tabs������ԴΪ viewpager adapter 
		tabLayout.setTabsFromPagerAdapter(vpAdapter);
		
		// ����FloatingActionButton ����¼�
		fActionButton.setOnClickListener(this);
	}

	/**
	 * �໬�˵����Ч��
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
			
			// ���ò໬�˵�ѡ�У�
			menuItem.setChecked(true);
			drawerLayout.closeDrawers();;
			
			// android support design�� Toast ��ʾ
			SnackbarUtil.show(viewPager, msg, 0);
			
			return true;
		}
	};
	
	/**
	 * 
	 *  Function: ��ʼ��ģ������
	 *  @author liuzheng
	 *  @created 2015��7��17�� ����10:42:03
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
	 *  Function: ��ʼ��view
	 *  @author liuzheng
	 *  @created 2015��7��17�� ����10:41:51
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
		// ����toolbar ����
		toolbar.setTitle(mTitles[arg0]);
	}
}
