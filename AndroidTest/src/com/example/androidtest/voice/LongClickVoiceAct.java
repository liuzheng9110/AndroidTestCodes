package com.example.androidtest.voice;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

public class LongClickVoiceAct extends Activity {

	private ListView mListView;
	private VoiceAdapter mAdapter;
	private ArrayList<VoiceEntity> mDataArrays = new ArrayList<VoiceEntity>();
	private TextView longClickVoiceBtn;

	private boolean isShosrt = false;
	private LinearLayout voice_rcd_hint_loading, voice_rcd_hint_rcding,
			voice_rcd_hint_tooshort;
	private LongClickVoiceSoundMeter mSensor;
	private View rcChat_popup;
	private ImageView chatting_mode_btn, volume;
	private int flag = 1;
	private Handler mHandler = new Handler();
	private String voiceName;
	private long startVoiceT, endVoiceT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.long_click_voice);

		initView();

		initData();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listView);
		longClickVoiceBtn = (TextView) findViewById(R.id.click_voice);

		// //////////////////
		volume = (ImageView) this.findViewById(R.id.volume);
		rcChat_popup = this.findViewById(R.id.rcChat_popup);
		voice_rcd_hint_rcding = (LinearLayout) this.findViewById(R.id.voice_rcd_hint_rcding);
		voice_rcd_hint_loading = (LinearLayout) this.findViewById(R.id.voice_rcd_hint_loading);
		voice_rcd_hint_tooshort = (LinearLayout) this.findViewById(R.id.voice_rcd_hint_tooshort);
		mSensor = new LongClickVoiceSoundMeter();
		// //////////////////

		longClickVoiceBtn.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// 按下语音录制按钮时返回false执行父类OnTouch
				
				return false;
			}
		});
	}

	private String[] msgArray = new String[] { "有人就有恩怨", "有恩怨就有江湖", "人就是江湖",
			"你怎么退出？ ", "生命中充满了巧合", "两条平行线也会有相交的一天。" };
	private String[] dataArray = new String[] { "2012-10-31 18:00",
			"2012-10-31 18:10", "2012-10-31 18:11", "2012-10-31 18:20",
			"2012-10-31 18:30", "2012-10-31 18:35" };
	private final static int COUNT = 6;

	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			VoiceEntity entity = new VoiceEntity();
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setMsgType(true);
			} else {
				entity.setMsgType(false);
			}

			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new VoiceAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!Environment.getExternalStorageDirectory().exists()) {
			Toast.makeText(this, "No SDCard", Toast.LENGTH_LONG).show();
			return false;
		}

		System.out.println("1");
		int[] location = new int[2];
		longClickVoiceBtn.getLocationInWindow(location); // 获取在当前窗口内的绝对坐标
		int btn_rc_Y = location[1];
		int btn_rc_X = location[0];
		if (event.getAction() == MotionEvent.ACTION_DOWN && flag == 1) {
			if (!Environment.getExternalStorageDirectory().exists()) {
				Toast.makeText(this, "No SDCard", Toast.LENGTH_LONG).show();
				return false;
			}
			System.out.println("2");
			if (event.getY() > btn_rc_Y && event.getX() > btn_rc_X) {// 判断手势按下的位置是否是语音录制按钮的范围内
				System.out.println("3");
				longClickVoiceBtn.setBackgroundResource(R.drawable.voice_rcd_btn_pressed);
				rcChat_popup.setVisibility(View.VISIBLE);
				voice_rcd_hint_loading.setVisibility(View.VISIBLE);
				voice_rcd_hint_rcding.setVisibility(View.GONE);
				voice_rcd_hint_tooshort.setVisibility(View.GONE);
				mHandler.postDelayed(new Runnable() {
					public void run() {
						if (!isShosrt) {
							voice_rcd_hint_loading.setVisibility(View.GONE);
							voice_rcd_hint_rcding.setVisibility(View.VISIBLE);
						}
					}
				}, 300);
				startVoiceT = System.currentTimeMillis();
				voiceName = startVoiceT + ".amr";
				start(voiceName);
				flag = 2;
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP && flag == 2) {// 松开手势时执行录制完成
			System.out.println("4");
			longClickVoiceBtn.setBackgroundResource(R.drawable.voice_rcd_btn_nor);

			voice_rcd_hint_rcding.setVisibility(View.GONE);
			stop();
			endVoiceT = System.currentTimeMillis();
			flag = 1;
			int time = (int) ((endVoiceT - startVoiceT) / 1000);

			if (time < 1) {
				isShosrt = true;
				voice_rcd_hint_loading.setVisibility(View.GONE);
				voice_rcd_hint_rcding.setVisibility(View.GONE);
				voice_rcd_hint_tooshort.setVisibility(View.VISIBLE);
				mHandler.postDelayed(new Runnable() {
					public void run() {
						voice_rcd_hint_tooshort.setVisibility(View.GONE);
						rcChat_popup.setVisibility(View.GONE);
						isShosrt = false;
					}
				}, 500);
				return false;
			}
			VoiceEntity entity = new VoiceEntity();
			entity.setDate(getDate());
			entity.setMsgType(false);
			entity.setTime(time + "\"");
			entity.setText(voiceName);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();
			mListView.setSelection(mListView.getCount() - 1);
			rcChat_popup.setVisibility(View.GONE);

		}
		return super.onTouchEvent(event);
	}

	private static final int POLL_INTERVAL = 300;

	private Runnable mSleepTask = new Runnable() {
		public void run() {
			stop();
		}
	};
	private Runnable mPollTask = new Runnable() {
		public void run() {
			double amp = mSensor.getAmplitude();
			updateDisplay(amp);
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);

		}
	};

	private void start(String name) {
		mSensor.start(name);
		mHandler.postDelayed(mPollTask, POLL_INTERVAL);
	}

	private void stop() {
		mHandler.removeCallbacks(mSleepTask);
		mHandler.removeCallbacks(mPollTask);
		mSensor.stop();
		volume.setImageResource(R.drawable.record_01);
	}

	private void updateDisplay(double signalEMA) {

		switch ((int) signalEMA) {
		case 0:
		case 1:
			// volume.setImageResource(R.drawable.amp1);
			// volume.setImageResource(R.drawable.record_animate_01);
			volume.setImageResource(R.drawable.record_01);
			break;
		case 2:
		case 3:
			// volume.setImageResource(R.drawable.amp2);
			volume.setImageResource(R.drawable.record_02);
			break;
		case 4:
		case 5:
			// volume.setImageResource(R.drawable.amp3);
			volume.setImageResource(R.drawable.record_03);
			break;
		case 6:
		case 7:
			// volume.setImageResource(R.drawable.amp4);
			volume.setImageResource(R.drawable.record_04);
			break;
		case 8:
		case 9:
			// volume.setImageResource(R.drawable.amp5);
			volume.setImageResource(R.drawable.record_05);
			break;
		case 10:
		case 11:
			// volume.setImageResource(R.drawable.amp6);
			volume.setImageResource(R.drawable.record_06);
			break;
		default:
			// volume.setImageResource(R.drawable.amp7);
			volume.setImageResource(R.drawable.record_07);
			break;
		}
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);

		return sbBuffer.toString();
	}

}
