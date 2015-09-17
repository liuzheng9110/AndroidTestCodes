package com.example.androidtest.media;

import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

public class MyMediaPlayer implements OnBufferingUpdateListener, OnPreparedListener {

	private MediaPlayer mediaPlayer;
	private SeekBar seekBar;
	private Timer timer = new Timer();

	public MyMediaPlayer(SeekBar seekBarProgress) {
		this.seekBar = seekBarProgress;
		
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
			
		} catch (Exception e) {
		}
		
		timer.schedule(mTimerTask, 0, 100);
	}
	
	TimerTask mTimerTask = new TimerTask() {
		
		@Override
		public void run() {
			if (mediaPlayer == null) {
				return;
			}
			
			if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
				handleProgress.sendEmptyMessage(0);
			}
		}
	};
	
	Handler handleProgress = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();
			
			if (duration > 0) {
				long pos = seekBar.getMax() * position / duration;
				seekBar.setProgress((int) pos);
			}
			
		};
	};
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public void play() {
		mediaPlayer.start();
	}
	
	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		
		seekBar.setProgress(0);
	}
	
	public void playUrl(String url) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare();
		} catch (Exception e) {
		}
	}
	
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i("liuz", "onPrepared...");
		mp.start();
		Log.i("liuz", "onPrepared... start ");
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		seekBar.setSecondaryProgress(percent);  
        int currentProgress = seekBar.getMax()*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration();  
       Log.i("liuz", "currentProgress...." + currentProgress);
	}
}
