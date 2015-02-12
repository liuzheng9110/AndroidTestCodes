package com.example.androidtest.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

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
public class NotificationCustomAct extends BaseActivity implements OnClickListener{
	
	private Button btn01, btn02;
	// 
	private PendingIntent lovePendingIntent;
	private PendingIntent prevPendingIntent;
	private PendingIntent playPendingIntent;
	private PendingIntent nextPendingIntent;
	private PendingIntent canclePendingIntent;
	private IntentFilter mIntentFilter;
	
	private BtnReceiver mBtnReceiver;
	private boolean isPlay = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("NotificationCustomAct");
		setContentView(R.layout.notification_custom_layout);

		initView();
		
		registerReceiver();
	}
	
	protected void initView() {
		mNotification = new Notification(R.drawable.ic_launcher_01, "...", System.currentTimeMillis());
		
		btn01 = (Button) findViewById(R.id.cus_notify_btn_01);
		btn02 = (Button) findViewById(R.id.cus_notify_btn_02);
		
		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cus_notify_btn_01:
			showCustomNotification();
			break;
		case R.id.cus_notify_btn_02:
			showCustomBtnNotification();
			break;
		}
	}
	
	private void registerReceiver() {
		mBtnReceiver = new BtnReceiver();
		mIntentFilter = new IntentFilter(NotificationConst.CUSTOM_BTN_ACTION);
		registerReceiver(mBtnReceiver, mIntentFilter);
		Log.i("liuz", "registerReceiver...");
	}

	/**
	 * 
	 *  Function:自定义通知
	 *  @author liuzheng
	 *  @created 2015年2月12日 上午11:01:14
	 */
	private void showCustomNotification() {
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.cus_notify_layout);
		mRemoteViews.setImageViewResource(R.id.image_notify, R.drawable.ic_launcher);
		mRemoteViews.setTextViewText(R.id.textview_title, "今日头条");
		mRemoteViews.setTextViewText(R.id.textview_content, "《刺杀金正恩》于app store正式上线");
		
		mNotification.contentView = mRemoteViews;
		mNotification.defaults = Notification.DEFAULT_ALL;
		mPendingIntent = PendingIntent.getActivity(this, 0, getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
		mNotification.contentIntent = mPendingIntent;
		mNotification.when = System.currentTimeMillis();
		mNotificationManager.notify(NotificationConst.MAIN_CUS_DEFAULT_ID, mNotification);
		
	}
	
	/**
	 * 
	 *  Function:带按钮的自定义通知
	 *  @author liuzheng
	 *  @created 2015年2月12日 上午11:01:00
	 */
	private void showCustomBtnNotification() {
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.cus_btn_notify_layout);
		mRemoteViews.setImageViewResource(R.id.cus_notify_image, R.drawable.eason);
		mRemoteViews.setTextViewText(R.id.cus_notify_title, "十年");
		mRemoteViews.setTextViewText(R.id.cus_notify_desc, "陈奕迅 - 黑白灰");
		
		if (isPlay) {
			mRemoteViews.setImageViewResource(R.id.cus_notify_play, R.drawable.note_btn_pause);
		}else {
			mRemoteViews.setImageViewResource(R.id.cus_notify_play, R.drawable.note_btn_play);
		}
		
		mIntent = new Intent(NotificationConst.CUSTOM_BTN_ACTION);
		// 喜欢
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_LIKE);
		lovePendingIntent = PendingIntent.getBroadcast(this, 1000, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_love, lovePendingIntent);
		// 上一首
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_PREV);
		prevPendingIntent = PendingIntent.getBroadcast(this, 1001, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_prev, prevPendingIntent);
		// 暂停/播放
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_PLAY);
		playPendingIntent = PendingIntent.getBroadcast(this, 1002, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_play, playPendingIntent);
		// 下一首
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_NEXT);
		nextPendingIntent = PendingIntent.getBroadcast(this, 1003, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_next, nextPendingIntent);
		// 取消
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_CANCLE);
		canclePendingIntent = PendingIntent.getBroadcast(this, 1004, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_cancle, canclePendingIntent);
		
		mNotification.contentView = mRemoteViews;
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;// 
		mNotification.defaults = Notification.DEFAULT_ALL;
		mNotification.tickerText = "正在播放...";
		mNotificationManager.notify(NotificationConst.MAIN_CUS_DEFAULT_ID, mNotification);
	}

	@Override
	protected void onDestroy() {
		if (mBtnReceiver != null) {
			unregisterReceiver(mBtnReceiver);
		}
		super.onDestroy();
	}
	
	/**
	 * 
	 *  Class Name: NotificationCustomAct.java
	 *  Function:自定义广播接收器
	 *  
	 *  @author liuzheng
	 *  @version 1.0 
	 *  @created 2015年2月12日 上午11:02:01
	 *  @Copyright http://liuz.me
	 */
	public class BtnReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(NotificationConst.CUSTOM_BTN_ACTION)) {
				int btnId = intent.getIntExtra(NotificationConst.BTN_KEY, 0);
				switch (btnId) {
				case NotificationConst.BTN_ID_LIKE:
					showShortToast("喜欢");
					break;
				case NotificationConst.BTN_ID_PREV:
					showShortToast("上一首");
					break;
				case NotificationConst.BTN_ID_PLAY:
					String playStatu = "";
					isPlay = !isPlay;
					if (isPlay) {
						playStatu = "开始播放";
					}else {
						playStatu = "已暂停";
					}
					showCustomBtnNotification();
					showShortToast(playStatu);
					break;
				case NotificationConst.BTN_ID_NEXT:
					showShortToast("下一首");
					break;
				case NotificationConst.BTN_ID_CANCLE:
					showShortToast("取消");
					mNotificationManager.cancel(NotificationConst.MAIN_CUS_DEFAULT_ID);
					break;
				}
			}
		}
	}
}
