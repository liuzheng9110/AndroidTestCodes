package com.example.androidtest.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatExtras;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidtest.AndroidNotificationAct;
import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: NotificationActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年2月11日 上午10:57:11
 *  @Copyright http://liuz.me
 *  http://www.eoeandroid.com/thread-562932-1-1.html
 *  http://blog.csdn.net/zywisdoml/article/details/42966193#t2
 */
public class NotificationActivity extends BaseActivity implements OnClickListener {

	private int[] notificationIds = { R.id.notification_simple, R.id.notification_always_show, R.id.notification_clear_one, R.id.notification_clear_all, 
			R.id.notification_customer, R.id.notification_progress };

	private Button[] btns = new Button[notificationIds.length];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		
		initView();
	}

	private void initView() {
		for (int i = 0; i < notificationIds.length; i++) {
			btns[i] = (Button) findViewById(notificationIds[i]);
			btns[i].setOnClickListener(this);
		}
		
		//在通知栏未下拉是 显示  图片 文字 
		mNotification = new Notification(R.drawable.fenzu_list_radio_select, "Notification", System.currentTimeMillis());
		mNotification.defaults = Notification.DEFAULT_LIGHTS; 
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notification_simple:// 简单通知
			// FLAG_ONLY_ALERT_ONCE 点击立马cancle
			mNotification.flags = Notification.FLAG_AUTO_CANCEL;
//			mNotification.defaults = Notification.DEFAULT_ALL; // 默认声音 震动  灯光
//			mNotification.sound=Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.mm); // 自定义声音 
			mNotification.setLatestEventInfo(this, "通知栏标题", "通知栏内容...简单通知", null);
			mNotificationManager.notify(NotificationConst.MAIN_DEFAULT_ID, mNotification);
			break;
		case R.id.notification_always_show:// 常驻通知
			// FLAG_NO_CLEAR 通知栏没有 清除 按钮 但是可以程序清除
			// FLAG_ONGOING_EVENT 重复提示
			mNotification.flags = Notification.FLAG_NO_CLEAR;
			mNotification.setLatestEventInfo(this, "常驻通知", "常驻通知内容", null);
			mNotificationManager.notify(NotificationConst.MAIN_NO_CLEAR, mNotification);
			break;
		case R.id.notification_clear_one:// 清除某一个通知
			mNotificationManager.cancel(NotificationConst.MAIN_DEFAULT_ID);
			break;
		case R.id.notification_clear_all:// 清除所有通知
			mNotificationManager.cancelAll(); // 程序清除的  所有
			break;
		case R.id.notification_click_intent:// 可点击通知  在通知栏添加点击事件  一般是三种事件 1.启动activity  2.启动service  3.发送广播broastcast 
			mNotification.flags = Notification.FLAG_ONLY_ALERT_ONCE; // 点击收起通知栏  并不消失
			mPendingIntent = PendingIntent.getActivity(this, 1001, new Intent(this, AndroidNotificationAct.class), PendingIntent.FLAG_UPDATE_CURRENT); // 事件Intent
			mNotification.setLatestEventInfo(this, "自定义通知标题", "自定义通知内容", mPendingIntent);
			mNotificationManager.notify(NotificationConst.MAIN_DEFAULT_ID, mNotification);
			break;
		case R.id.notification_customer:// 自定义通知
			startActivity(new Intent(this, NotificationCustomAct.class));
			break;
		case R.id.notification_progress:// 自定义进度条通知
			startActivity(new Intent(this, NotificationProgressAct.class));
			break;
		}
	}
	
}
