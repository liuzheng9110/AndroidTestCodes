package com.example.androidtest.notification;

import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: NotificationCustomAct.java
 *  Function:自定义通知栏信息
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年2月11日 下午4:28:43
 *  @Copyright http://liuz.me
 */
public class NotificationCustomAct extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("NotificationCustomAct");
		setContentView(R.layout.notification_custom_layout);
		registerReceiver();
	}
	
	private void registerReceiver() {
		
	}

	public void click_listener(View v){
		switch (v.getId()) {
		case R.id.notification_customer_btn_01:
			
			break;
		case R.id.notification_customer_btn_02:
			
			break;
		}
	}
	
}
