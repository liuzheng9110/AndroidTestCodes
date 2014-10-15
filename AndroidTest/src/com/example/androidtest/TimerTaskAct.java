package com.example.androidtest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class TimerTaskAct extends Activity {

	private final String TAG = "TimerTask";
	private final int EVENT_LOCK_WINDOW = 0x100;

	private TextView textView;
	private Handler mHandler;
	private Timer mTimer;
	private MyTimerTask mTimerTask;
	private int time = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_task);

		textView = (TextView) findViewById(R.id.textview);

		mHandler = new Handler() {
			public void handleMessage(Message message) {
				Log.i(TAG, "message what = " + message.arg1);
				if(message.arg1 == 30){
					setTextMsg("已录制 "+message.arg1+" 秒");
					mTimer.cancel();
				} else if (message.arg1 >= 25) {
					setTextMsg("还可录制 " + (30-message.arg1) + " 秒");
				} else {
					setTextMsg("已录制 "+time+" 秒");
				}
			}
		};

		mTimer = new Timer(true);

		textView.setText("main window");

		voiceRecordTimer();
	}

	protected void setTextMsg(String str) {
		textView.setText(str);
	}

	public void voiceRecordTimer() {
		if (mTimer != null) {
			if (mTimerTask != null) {
				mTimerTask.cancel(); // 将原任务从队列中移除
			}
			mTimerTask = new MyTimerTask(); // 新建一个任务
//			mTimer.schedule(mTimerTask, 1000, 1000);
			
			mTimer.scheduleAtFixedRate(mTimerTask, 0, 1000);
		}
	}

	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			int timeNum = time++;
			Log.i(TAG, "run..." + timeNum);
			Message msg = new Message();
			msg.arg1 = timeNum;
			mHandler.sendMessage(msg);
		}
	}
}
