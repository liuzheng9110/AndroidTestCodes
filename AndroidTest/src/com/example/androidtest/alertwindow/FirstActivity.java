package com.example.androidtest.alertwindow;

import com.example.androidtest.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class FirstActivity extends Activity {
	private WakeLock mWakelock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		PendingIntent piIntent = PendingIntent.getActivity(this, 1, new Intent(this, LockAlertWindow.class), 0);
		am.set(AlarmManager.RTC_WAKEUP, 5000, piIntent);
		
		setContentView(R.layout.alert_window);
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mWakelock.release();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
		mWakelock.acquire();
	}
}
