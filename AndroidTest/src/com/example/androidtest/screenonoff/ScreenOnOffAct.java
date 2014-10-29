package com.example.androidtest.screenonoff;

import android.os.Bundle;
import android.util.Log;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.main.MyApplication;
import com.example.androidtest.screenonoff.ScreenObserver.ScreenStateListener;

public class ScreenOnOffAct extends BaseActivity {
	private static String TAG = "liuz";
	private ScreenObserver mScreenObserver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_onoff_layout);

		setTitle("ScreenOnOffAct");
		
		mScreenObserver = new ScreenObserver(this);
		mScreenObserver.requestScreenStateUpdate(new ScreenStateListener() {
			@Override
			public void onScreenOn() {
				doSomethingOnScreenOn();
			}

			@Override
			public void onScreenOff() {
				doSomethingOnScreenOff();
			}
		});
	}

	private void doSomethingOnScreenOn() {
		Log.i(TAG, "Screen is on");
	}

	private void doSomethingOnScreenOff() {
		Log.i(TAG, "Screen is off");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//Í£Ö¹¼àÌýscreen×´Ì¬
		mScreenObserver.stopScreenStateUpdate();
		MyApplication.getInstance().stopTimer();
	}
}