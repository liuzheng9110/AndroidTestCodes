package com.example.androidtest.main;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

public class BaseActivity extends Activity {
	
	private Toast mToast;

	private int time = 0;
	private int period = 5 * 1000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("liuz", "onTouchEvent...ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("liuz", "onTouchEvent...ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("liuz", "onTouchEvent...ACTION_UP");
			
			MyApplication.getInstance().stopTimer();
			MyApplication.getInstance().startTimer();
			
			break;
		default:
			break;
		}
		
		return true;
	}
	
	/**
	 * �Զ��������
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
		Log.i("liuz", "onResume...");
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		Log.i("liuz", "onStart...");
		MyApplication.getInstance().startTimer();
		
		try {
			time = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
			Log.i("liuz", "time...." + time + "...period...." + period);
			
			if (time > period) {
				MyApplication.getInstance().startTimer();
			}
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
		super.onStart();
	}
}