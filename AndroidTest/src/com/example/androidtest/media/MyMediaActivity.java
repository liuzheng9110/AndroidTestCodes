package com.example.androidtest.media;

import java.util.Timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: MyMediaActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月23日 下午9:52:38
 *  @Copyright http://liuz.me 
 *  @url http://blog.csdn.net/hellogv/article/details/6406732
 */
public class MyMediaActivity extends BaseActivity implements OnClickListener, OnSeekBarChangeListener {
	private Button playBtn, pauseBtn, stopBtn;
	private SeekBar seekBar;
	private MyMediaPlayer mPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("liuz", "onCreate...");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_media_layout);
		
		playBtn = (Button) findViewById(R.id.btnPlayUrl);
		pauseBtn = (Button) findViewById(R.id.btnPause);
		stopBtn = (Button) findViewById(R.id.btnStop);
		
		playBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		
		seekBar = (SeekBar) findViewById(R.id.skbProgress);
		seekBar.setOnSeekBarChangeListener(this);
		
		mPlayer = new MyMediaPlayer(seekBar);
		
		mPlayer.playUrl("http://yinyueshiting.baidu.com/data2/music/134379471/12625981437512461128.mp3?xcode=25f834c3e4fd98794394f800f7f2550e");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPlayUrl:
			mPlayer.playUrl("http://yinyueshiting.baidu.com/data2/music/134379471/12625981437512461128.mp3?xcode=25f834c3e4fd98794394f800f7f2550e");
			break;
		case R.id.btnPause:
			mPlayer.pause();
			break;
		case R.id.btnStop:
			mPlayer.stop();
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Log.i("liuz", "isPlaying..." + mPlayer.getMediaPlayer().isPlaying());
		
		if (mPlayer.getMediaPlayer().isPlaying()) {
			mPlayer.stop();
			mPlayer.playUrl("http://yinyueshiting.baidu.com/data2/music/134379471/12625981437512461128.mp3?xcode=25f834c3e4fd98794394f800f7f2550e");
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("liuz", "onPause....");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mPlayer.stop();
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
