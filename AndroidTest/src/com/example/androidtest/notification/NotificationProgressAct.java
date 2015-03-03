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
import android.widget.RemoteViews;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 * Class Name: NotificationProgressAct.java Function: 自定义通知栏进度条
 * 
 * @author liuzheng
 * @version 1.0
 * @created 2015年3月2日 下午4:00:20
 * @Copyright http://liuz.me
 */
public class NotificationProgressAct extends BaseActivity {

	private IntentFilter mIntentFilter;
	private ClearBroadcast mClearBroadcast;
	
	private int mCurrentProgress = 0;
	private int mLastProgress = 0;
	private boolean mIsPause = false;
	private DownloadThread mDownloadThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("NotificationProgressAct");
		setContentView(R.layout.notification_progress_layout);
		// 注册点击通知栏操作广播
		doRegisterReceiver();
	}

	/**
	 * 
	 *  Function: 注册自定义广播
	 *  @author liuzheng
	 *  @created 2015年3月2日 下午4:31:00
	 */
	public void doRegisterReceiver() {
		mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(NotificationConst.CLEAR_BROADCAST_ACTION);
		mClearBroadcast = new ClearBroadcast();
		registerReceiver(mClearBroadcast, mIntentFilter);
	}
	
	
	@Override
	protected void onDestroy() {
		if (mClearBroadcast != null) {
			unregisterReceiver(mClearBroadcast);
		}
		super.onDestroy();
	}
	
	/**
	 * 
	 *  Class Name: NotificationProgressAct.java
	 *  Function: 自定义广播处理
	 *  
	 *  @author liuzheng
	 *  @version 1.0 
	 *  @created 2015年3月2日 下午4:32:39
	 *  @Copyright http://liuz.me
	 */
	public class ClearBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("liuz", "action..." + intent.getAction());
			if (intent.getAction().equals(NotificationConst.CLEAR_BROADCAST_ACTION)) {
				showShortToast("点击清楚所有通知...");
			}
		}
	}
	
	/**
	 * 
	 *  Function:默认进度条通知
	 *  @author liuzheng
	 *  @created 2015年3月2日 下午4:42:25
	 */
	private void showDefaultProgressNotify(boolean indeterminate) {
		// 初始化通知对象
		mNotification = new Notification();
		// 定义通知栏下拉显示布局
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.notification_progress_default_layout);
		mRemoteViews.setTextViewText(R.id.notification_default_text, "默认进度条正在下载...");
		mRemoteViews.setProgressBar(R.id.notification_default_progress, 100, 50, indeterminate);// indeterminate 是否不定的  t 有动态效果 f 无
		// 设置通知栏布局显示
		mNotification.contentView = mRemoteViews;
		mNotification.icon = R.drawable.ic_launcher;
		mNotification.tickerText = "默认进度条正在下载...";
		mNotification.defaults = Notification.DEFAULT_VIBRATE; // 默认通知 震动
		
		// 清楚通知时提醒
		mIntent = new Intent(NotificationConst.CLEAR_BROADCAST_ACTION);
		mPendingIntent = PendingIntent.getBroadcast(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mNotification.deleteIntent = mPendingIntent;
		
		// 开启通知
		mNotificationManager.notify(NotificationConst.MAIN_PROGRESS_DEFAULT_ID, mNotification);
	}
	
	/**
	 * 
	 *  Function:非默认进度条
	 *  @author liuzheng
	 *  @created 2015年3月3日 上午10:44:16
	 */
	private void showNonDedaultProgressNotify(boolean indeterminate) {
		// 初始化通知对象
		mNotification = new Notification();
		// 定义通知栏下拉显示布局
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.notification_progress_nondefault_layout);
		mRemoteViews.setTextViewText(R.id.notification_nondefault_text, "非默认进度条正在下载...");
		mRemoteViews.setProgressBar(R.id.notification_nondefault_progress, 100, 50, indeterminate); 
		// 设置通知栏布局显示
		mNotification.contentView = mRemoteViews;
		mNotification.icon = R.drawable.ic_launcher_01;
		mNotification.tickerText = "非默认进度条正在下载...";
		mNotification.defaults = Notification.DEFAULT_VIBRATE; // 默认通知 震动

		// 清楚通知时提醒
		mIntent = new Intent(NotificationConst.CLEAR_BROADCAST_ACTION);
		mPendingIntent = PendingIntent.getBroadcast(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mNotification.deleteIntent = mPendingIntent;

		// 开启通知
		mNotificationManager.notify(NotificationConst.MAIN_PROGRESS_DEFAULT_ID, mNotification);
	}
	
	/**
	 * 
	 *  Function:自定义进度条
	 *  @author liuzheng
	 *  @created 2015年3月3日 下午4:18:11 
	 *  @param indeterminate
	 */
	private void showCusProgressNotify(boolean indeterminate, String title) {
		mNotification = new Notification();
		
		mRemoteViews = new RemoteViews(getPackageName(), R.layout.notification_progress_cus_layout);
		mRemoteViews.setTextViewText(R.id.notification_progress_cus_text, title);
		
		if (mCurrentProgress >= 100 || mDownloadThread == null) { // 下载完成
			mRemoteViews.setProgressBar(R.id.notification_progress_cus_progressbar, 0, 0, indeterminate);
			mRemoteViews.setViewVisibility(R.id.notification_progress_cus_progressbar, View.GONE);
		}else{ // 正在下载...
			mRemoteViews.setViewVisibility(R.id.notification_progress_cus_progressbar, View.VISIBLE);
			mRemoteViews.setProgressBar(R.id.notification_progress_cus_progressbar, 100, mLastProgress, indeterminate);
		}
		
		mNotification.contentView = mRemoteViews;
		mNotification.defaults = Notification.DEFAULT_VIBRATE;
		mNotification.icon = R.drawable.ic_launcher_02;
		mNotification.tickerText = "downloading......";
		
		mNotificationManager.notify(NotificationConst.MAIN_PROGRESS_DEFAULT_ID, mNotification);
		
	}
	
	/**
	 * 
	 *  Class Name: NotificationProgressAct.java
	 *  Function: 下载线程
	 *  
	 *  @author liuzheng
	 *  @version 1.0 
	 *  @created 2015年3月3日 上午10:14:00
	 *  @Copyright http://liuz.me
	 */
	class DownloadThread extends Thread{
		@Override
		public void run() {
			
		}
	}
	
	/**
	 * 按钮事件
	 */
	@Override
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.notification_customer_progress_01:
			showShortToast("show default progress notify");
			mDownloadThread = null;
			showDefaultProgressNotify(true);
			break;
		case R.id.notification_customer_progress_02:
			showShortToast("show non-default progress notify");
			mDownloadThread = null;
			showNonDedaultProgressNotify(true);
			break;
		case R.id.notification_customer_progress_03:
			showShortToast("show cus progress notify");
			mDownloadThread = null;
			showCusProgressNotify(false, "wait for download......");
			break;
		case R.id.notification_customer_progress_04:
			showShortToast("start download notify");
			break;
		case R.id.notification_customer_progress_05:
			showShortToast("pause download notify");
			break;
		case R.id.notification_customer_progress_06:
			showShortToast("cancle download notify");
			break;
		default:
			break;
		}
	}

}
