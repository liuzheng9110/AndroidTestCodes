package com.example.androidtest.main;

import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

public class BaseActivity extends Activity {
	
	private ProgressDialog progressDialog;
	
	public MyApplication myApplication;
	
	private Toast mToast;

	private int time = 0;
	private int period = 5 * 1000;
	
	private SimpleBroadcastReceiver mReceiver;
	
	//////////2015-02-10 18:13:04  notification ///////
	public NotificationManager mNotificationManager;
	public Notification mNotification;
	public PendingIntent mPendingIntent;
	public Intent mIntent;
	public RemoteViews mRemoteViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mReceiver = new SimpleBroadcastReceiver();
		
		myApplication = (MyApplication) getApplication();
		
		//////////2015-02-10 18:13:04  notification ///////
		// 获取通知管理对象
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		
	}
	
	protected void showShortToast(String msg){
		showToast(msg, Toast.LENGTH_SHORT);
	}
	protected void showLongToast(String msg){
		showToast(msg, Toast.LENGTH_LONG);
	}
	
	private void showToast(String msg, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(this, msg, duration);
		}else {
			mToast.setText(msg);
			mToast.setDuration(duration);
		}
		mToast.show();
	}
	
	protected void showProgressDialog(){
		progressDialog = new ProgressDialog(this, android.R.style.Widget_Holo_ProgressBar);
		progressDialog.setTitle("title");
		progressDialog.setMessage("正在加载中...");
		progressDialog.show();
	}
	
	protected void  dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			Log.i("liuz", "onTouchEvent...ACTION_DOWN");
//			break;
//		case MotionEvent.ACTION_MOVE:
//			Log.i("liuz", "onTouchEvent...ACTION_MOVE");
//			break;
//		case MotionEvent.ACTION_UP:
//			Log.i("liuz", "onTouchEvent...ACTION_UP");
//			
//			MyApplication.getInstance().stopTimer();
//			MyApplication.getInstance().startTimer();
//			
//			break;
//		default:
//			break;
//		}
//		
//		return true;
//	}
	
	/**
	 * 自定义标题栏
	 * 
	 * @param title
	 */
	protected void setCustomTitle(String title) {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_layout);
		TextView titleTv = (TextView) findViewById(R.id.title_center);
		Button leftBtn = (Button) findViewById(R.id.title_left);
		Button rightBtn = (Button) findViewById(R.id.title_right);
		
		titleTv.setText(title);
		leftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leftBtnListener();
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rightBtnListener();
			}
		});
		
	}

	protected void leftBtnListener() {}
	protected void rightBtnListener() {}

	@Override
	protected void onResume() {
//		Log.i("liuz", "onResume...");
		
//		if (!MyApplication.getInstance().isStart()) {
//			MyApplication.getInstance().startTimer();
//		}
//		registerScreenBroadcastReceiver();
		super.onResume();
	}
	
	@Override
	protected void onStart() {
//		Log.i("liuz", "onStart...");
		
//		if (!MyApplication.getInstance().isLock()) {
////			MyApplication.getInstance().startTimer();
//			try {
//				time = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
//				Log.i("liuz", "time...." + time + "...period...." + period);
//				
//				if (time > period) {
//					MyApplication.getInstance().startTimer();
//				}
//			} catch (SettingNotFoundException e) {
//				e.printStackTrace();
//			}
//			
//		}
		super.onStart();
	}
	
	
	///////////////////////////////////
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		Log.i("liuz", "screenBroadcastReceiver反注册了");
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	private void registerScreenBroadcastReceiver() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_SCREEN_OFF);// 当屏幕锁屏的时候触发
		intentFilter.addAction(Intent.ACTION_SCREEN_ON);// 当屏幕解锁的时候触发
//		intentFilter.addAction(Intent.ACTION_USER_PRESENT);// 当用户重新唤醒手持设备时触发
//		registerReceiver(mReceiver, intentFilter);
//		Log.i("liuz", "screenBroadcastReceiver注册了");
	}
	
	class SimpleBroadcastReceiver extends BroadcastReceiver{
		String action = null;
		@Override
		public void onReceive(Context context, Intent intent) {
			action = intent.getAction();
			Log.i("liuz", "action.........." + action);
			if (Intent.ACTION_SCREEN_ON.equals(action)) {
				Log.i("liuz", "ACTION_SCREEN_ON..........");
			}else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				Log.i("liuz", "ACTION_SCREEN_OFF..........");
			}
		}
	}
	
}
