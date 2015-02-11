package com.example.androidtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class AndroidNotificationAct extends Activity {

	private Button sendBtn, cancleBtn;
	private NotificationManager notificationManager;
	private Notification notification;
	private PendingIntent pendingIntent;
	private int notify_id;
	private NotificationCompat.Builder mBuilder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_notification_layout);
		
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
	}

	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.send_btn_01:
			notify_id = 100001;
			Intent intent = new Intent();
			intent.setClass(this, Android_DateActivity.class);
			pendingIntent = PendingIntent.getActivity(AndroidNotificationAct.this, 0, intent, 0);
			
			// method 01  app.Notification
			notification = new Notification(R.drawable.ic_launcher, "My Simple Notification Title", System.currentTimeMillis());
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.setLatestEventInfo(AndroidNotificationAct.this, "...", "下载成功，点击打开", pendingIntent);
			notificationManager.notify("app_upd", notify_id, notification);
			
			break;
			
		case R.id.send_btn_02:
			// method 012 v4.Notification
			/**
			* 1、Content title
			* 2、Large icon
			* 3、Content text
			* 4、Content info
			* 5、Small icon
			* 6、Time that the notification was issued. You can set an explicit value with setWhen(); 
			*    if you don't it defaults to the time that the system received the notification.
			*/
			// simple notification
			notify_id = 10002;
			mBuilder.setContentTitle("My Simple Notification Title")// 1
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_03))//2
					.setContentText("My Simple Notification Context")// 3
					.setContentInfo("99")// 4
					.setSmallIcon(R.drawable.icon_face)//5
					.setWhen(System.currentTimeMillis())//6
					.setAutoCancel(true)// 点击后 自动消失   默认点击不消失
					.setContentIntent(pendingIntent)// 设置点击后 打开的intent
					.setDefaults(Notification.DEFAULT_LIGHTS)// 设置提醒方式  震动   铃声   呼吸灯   所有
					.setNumber(999)
					;
			notificationManager.notify(notify_id, mBuilder.build());
			break;
			
		case R.id.send_btn_03:
			// simple change notification
			notify_id = 10003;
			mBuilder.setContentTitle("My Simple Notification Title")
					.setContentText("My Simple Notification Context");
			notificationManager.notify(notify_id, mBuilder.build());
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mBuilder.setContentText("My Simple Notification Context" + i);
				notificationManager.notify(notify_id, mBuilder.build());
			}
			break;
			
		case R.id.send_btn_04:
			// simple progress notification
			notify_id = 10004;
			mBuilder.setContentTitle("File is Downloading....")
					.setContentText("Download File....")
					.setSmallIcon(R.drawable.icon_face);
			new Thread(
				new Runnable() {
					public void run() {
						int index;
						for (index = 0; index < 100; index += 5) {
							mBuilder.setProgress(100, index, false);// 
							notificationManager.notify(notify_id, mBuilder.build());
							try {
								Thread.sleep(5*1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						mBuilder.setContentText("Download Complete....").setProgress(0, 0, false);
						notificationManager.notify(notify_id, mBuilder.build());
						}
					}
				).start();
			break;
			
		case R.id.send_btn_05:
			notify_id = 10005;
			
			Intent dismissIntent = new Intent(this, CusDatePickerAct.class);
			PendingIntent piDismiss = PendingIntent.getActivity(this, 0, dismissIntent, 0);

			Intent snoozeIntent = new Intent(this, CusDatePickerShowAct.class);
			PendingIntent piSnooze = PendingIntent.getActivity(this, 0, snoozeIntent, 0);
			
			mBuilder.setContentTitle("File is Downloading....")
					.setContentText("Download File....")
					.setSmallIcon(R.drawable.icon_face)
	        		.setDefaults(Notification.DEFAULT_ALL)
	        		.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.scrollbar_text)))
	        		.addAction (R.drawable.icon_input, "文字", piDismiss)
	        		.addAction (R.drawable.icon_face, "表情", piSnooze);
	        		;

			notificationManager.notify(notify_id, mBuilder.build());
			break;
			
		case R.id.cancle_btn:
			notificationManager.cancel(notify_id);
			break;
		default:
			break;
		}
	}
}
