package com.example.androidtest.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
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
 *  
 *  http://blog.csdn.net/vipzjyno1/article/details/25248021
 *  
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
	private boolean isPlaying = false;
	private boolean isLoveing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("NotificationCustomAct");
		setContentView(R.layout.notification_custom_layout);

		initView();
		
		doRegisterReceiver();
	}
	
	protected void initView() {
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
			// 不同版本 不同RemoteView 布局    
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // >= 16 可以使用 bigContentView   可以自定义通知栏显示高度
				showCustomBtnNotification();
			}else { // < 16 只能使用 contentView 即系统默认通知栏显示高度 
				showShortToast("SDK.Version < 16, 稍候完善......");
			}
			break;
		}
	}
	
	private void doRegisterReceiver() {
		mBtnReceiver = new BtnReceiver();
		mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(NotificationConst.CUSTOM_BTN_ACTION);
		registerReceiver(mBtnReceiver, mIntentFilter);
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
		
		mNotification = new Notification(R.drawable.ic_launcher_01, "...", System.currentTimeMillis());
		mNotification.contentView = mRemoteViews;
		mNotification.defaults = Notification.DEFAULT_ALL;
		mPendingIntent = PendingIntent.getActivity(this, 0, getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
		mNotification.contentIntent = mPendingIntent;
		mNotification.when = System.currentTimeMillis();
		mNotificationManager.notify(NotificationConst.MAIN_CUS_DEFAULT_ID, mNotification);
	}
	
	
	private int[] cusNotifyImages = {R.drawable.eason, R.drawable.wangfei, R.drawable.gem};
	private String[] cusNotifyTitles = {"十年", "匆匆那年", "后会无期"};
	private String[] cusNotifyDescs = {"陈奕迅 - 黑白灰", "王菲 - 匆匆那年", "G.E.M.邓紫棋 - 后会无期"};
	
	/**
	 * 
	 *  Function:带按钮的自定义通知
	 *  @author liuzheng
	 *  @created 2015年2月12日 上午11:01:00
	 */
	private void showCustomBtnNotification() {
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.cus_btn_notify_layout);
		
		// 设置通知栏布局图文数据
		setRemoteViewData(0);
        
		mIntent = new Intent(NotificationConst.CUSTOM_BTN_ACTION);
		// 喜欢
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_LIKE);
		lovePendingIntent = PendingIntent.getBroadcast(this, 1001, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_love, lovePendingIntent);
		// 上一首
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_PREV);
		prevPendingIntent = PendingIntent.getBroadcast(this, 1002, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_prev, prevPendingIntent);
		// 暂停/播放
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_PLAY);
		playPendingIntent = PendingIntent.getBroadcast(this, 1003, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_play, playPendingIntent);
		// 下一首
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_NEXT);
		nextPendingIntent = PendingIntent.getBroadcast(this, 1004, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_next, nextPendingIntent);
		// 取消
		mIntent.putExtra(NotificationConst.BTN_KEY, NotificationConst.BTN_ID_CANCLE);
		canclePendingIntent = PendingIntent.getBroadcast(this, 1005, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.cus_notify_cancle, canclePendingIntent);
		
		mNotification = new Notification(R.drawable.ic_launcher, "正在播放...", System.currentTimeMillis());
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 1111, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
		mNotification.contentView = mRemoteViews;  	// 一行显示(必须)
		mNotification.bigContentView = mRemoteViews;// 多行显示(API 16+ 非必须  如果未设置  则显示一行  设置则显示多行)
		mNotification.contentIntent = pendingIntent;
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;
		mNotificationManager.notify(NotificationConst.MAIN_CUS_DEFAULT_ID, mNotification);
		
	}

	/**
	 * 
	 *  Function:设置 RemoteView
	 *  @author liuzheng
	 *  @created 2015年3月2日 下午3:29:58 
	 *  @param i 模拟数据数组下标
	 */
	private void setRemoteViewData(int i) {
		if (i > cusNotifyTitles.length) { // 防止越界
			return;
		}
		
		mRemoteViews.setImageViewResource(R.id.cus_notify_image, cusNotifyImages[i]);
		mRemoteViews.setTextViewText(R.id.cus_notify_title, cusNotifyTitles[i]);
		mRemoteViews.setTextViewText(R.id.cus_notify_desc, cusNotifyDescs[i]);
		
		if (mNotification != null) { // 注意 mRemoteViews 修改  需要同步修改 mNotification.contentView/bigContentView
			mNotification.contentView = mRemoteViews;  	// 一行显示(必须)
			mNotification.bigContentView = mRemoteViews;// 多行显示(API 16+ 非必须  如果未设置  则显示一行  设置则显示多行)
		}
	}

	/**
	 * 
	 *  Class Name: NotificationCustomAct.java
	 *  Function:自定义广播接收器  处理通知栏按钮点击事件
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
					isLoveing = !isLoveing;
					
					if (isLoveing) {
						mRemoteViews.setImageViewResource(R.id.cus_notify_love, R.drawable.note_btn_loved);
					}else{
						mRemoteViews.setImageViewResource(R.id.cus_notify_love, R.drawable.note_btn_love);
					}
					
					showShortToast("喜欢");
					break;
				case NotificationConst.BTN_ID_PREV:
					showShortToast("上一首");
					
					// 设置通知栏布局图文数据
					setRemoteViewData(1);
					
					break;
				case NotificationConst.BTN_ID_PLAY:
					String playStatu = "";
					isPlaying = !isPlaying;
					
					// 更新通知栏布局  
					if (isPlaying) { 
						playStatu = "开始播放";
						mRemoteViews.setImageViewResource(R.id.cus_notify_play, R.drawable.note_btn_pause);
					}else {
						mRemoteViews.setImageViewResource(R.id.cus_notify_play, R.drawable.note_btn_play);
						playStatu = "已暂停";
					}
					
					showShortToast(playStatu);
					break;
				case NotificationConst.BTN_ID_NEXT:
					showShortToast("下一首");
					
					// 设置通知栏布局图文数据
					setRemoteViewData(2);
					
					break;
				case NotificationConst.BTN_ID_CANCLE:
					showShortToast("取消");
					mNotificationManager.cancel(NotificationConst.MAIN_CUS_DEFAULT_ID);
					break;
				}
				
				// 除取消外   love pre pause/play next 操作都需要更新通知布局
				if (btnId != NotificationConst.BTN_ID_CANCLE) {
					// 通知更新  注意id统一
					mNotificationManager.notify(NotificationConst.MAIN_CUS_DEFAULT_ID, mNotification);
				}
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		if (mBtnReceiver != null) {
			unregisterReceiver(mBtnReceiver);
		}
		super.onDestroy();
	}
}
