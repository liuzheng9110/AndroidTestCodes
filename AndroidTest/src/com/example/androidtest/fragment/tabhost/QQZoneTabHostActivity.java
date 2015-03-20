package com.example.androidtest.fragment.tabhost;

import com.example.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 
 *  Class Name: QQZoneTabHostActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年3月20日 下午5:25:17
 *  @Copyright http://liuz.me 
 *  @url  http://blog.csdn.net/yangyu20121224/article/details/9023451
 */
public class QQZoneTabHostActivity extends FragmentActivity implements OnClickListener {
	
	private LayoutInflater mInflater;
	private FragmentManager fManager;

	private FrameLayout friendFeedLayout, myFeedLayout, homeLayout, moreLayout, addLayout;
	private ImageView imgAddBgIv, imgAddIv;
	private boolean isShow = false;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.qqzone_tab_host_layout);
		setTitle("QQZoneTabHost");

		mInflater = LayoutInflater.from(this);
		fManager = getSupportFragmentManager();
		
		initView();
		
		// 初始操作
		fManager.beginTransaction().replace(R.id.content_layout, new Fragment_02()).commit();
		friendFeedLayout.setSelected(true);
	}

	private void initView() {
		// 布局
		friendFeedLayout = (FrameLayout) findViewById(R.id.layout_friendfeed);
		myFeedLayout = (FrameLayout) findViewById(R.id.layout_myfeed);
		homeLayout = (FrameLayout) findViewById(R.id.layout_home);
		moreLayout = (FrameLayout) findViewById(R.id.layout_more);
		addLayout = (FrameLayout) findViewById(R.id.layout_add);
		
		// 
		imgAddBgIv = (ImageView) findViewById(R.id.img_add_bg);
		imgAddIv = (ImageView) findViewById(R.id.img_add_btn);
		
		// init click
		friendFeedLayout.setOnClickListener(this);
		myFeedLayout.setOnClickListener(this);
		homeLayout.setOnClickListener(this);
		moreLayout.setOnClickListener(this);
		addLayout.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_friendfeed:
			fManager.beginTransaction().replace(R.id.content_layout, new Fragment_02()).commit();
			
			friendFeedLayout.setSelected(true);
			myFeedLayout.setSelected(false);
			homeLayout.setSelected(false);
			moreLayout.setSelected(false);
			
			break;
		case R.id.layout_myfeed:
			
			friendFeedLayout.setSelected(false);
			myFeedLayout.setSelected(true);
			homeLayout.setSelected(false);
			moreLayout.setSelected(false);
			
			break;
		case R.id.layout_home:

			friendFeedLayout.setSelected(false);
			myFeedLayout.setSelected(false);
			homeLayout.setSelected(true);
			moreLayout.setSelected(false);
			
			break;
		case R.id.layout_more:

			friendFeedLayout.setSelected(false);
			myFeedLayout.setSelected(false);
			homeLayout.setSelected(false);
			moreLayout.setSelected(true);
			
			break;
		// img add
		case R.id.layout_add:
			if (isShow) {
				isShow = false;
				imgAddBgIv.setImageResource(R.drawable.toolbar_btn_normal);
				imgAddIv.setImageResource(R.drawable.toolbar_plus);
			} else {
				isShow = true;
				imgAddBgIv.setImageResource(R.drawable.toolbar_btn_pressed);
				imgAddIv.setImageResource(R.drawable.toolbar_plusback);
			}
			break;
		default:
			break;
		}
	}
	
	private void showPopupwindow(){
		
	}
}
