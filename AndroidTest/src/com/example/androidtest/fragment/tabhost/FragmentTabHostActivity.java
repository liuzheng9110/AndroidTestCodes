package com.example.androidtest.fragment.tabhost;

import com.example.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

/**
 * 
 *  Class Name: FragmentTabHostActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年3月20日 上午8:52:46
 *  @Copyright http://liuz.me 
 *  @url
 */
public class FragmentTabHostActivity extends FragmentActivity {
	
	private LayoutInflater mInflater;
	private FragmentTabHost fTabHost;

//	private FragmentManager fManager;
//	private FragmentTransaction fTransaction;
	
	private int[] imageViewSelArr = {R.drawable.tab_reply, R.drawable.tab_news, R.drawable.tab_find, R.drawable.tab_mine};
	private String[] imageTextArr = {"聊天", "动态", "发现", "我的"};
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

//		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		setContentView(R.layout.fragment_tabhost_layout);
		
		initView();
	}

	private void initView() {
		mInflater  = LayoutInflater.from(this);
		
		fTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		fTabHost.setup(this, getSupportFragmentManager(), R.id.fragment_content);
		
		int count = imageTextArr.length;
		for (int i = 0; i < count; i++) {
			TabSpec tabSpec = fTabHost.newTabSpec(imageTextArr[i]).setIndicator(getTabItemView(i));
			
			Bundle bundle = new Bundle();
			bundle.putInt("key",i);
			
			fTabHost.addTab(tabSpec, Fragment_01.class, bundle);
		}
	}

	private View getTabItemView(int index) {
		View view = LayoutInflater.from(this).inflate(R.layout.fragment_tabhost_item_layout, null);
		
		ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
		imageView.setImageResource(imageViewSelArr[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.image_text);
		textView.setText(imageTextArr[index]);
		
		return view;
	}
}
