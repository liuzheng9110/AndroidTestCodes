package com.example.androidtest.main;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.androidtest.screenonoff.ScreenPwdAct;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.DisplayMetrics;
import android.util.Log;

public class MyApplication extends Application {
	private static MyApplication instance;
	private List<Activity> activityList;
	private Context context;
	
	private Timer mTimer;
	private MyTimerTask mTimerTask;
	public static int touchTime = 0;
	private int time = 0;
	private int period = 5 * 1000;
	private boolean isLock = false;
	private boolean isStart = false;
//	public static int period = 20 * 60 * 1000;
	
	public MyApplication() {
	}
	
	public static MyApplication getInstance(){
		if(instance == null){
			instance = new MyApplication();
		}
		return instance;
	}
	
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	
	public boolean isLock() {
		return isLock;
	}
	
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
	public boolean isStart() {
		return isStart;
	}

	@Override
	public void onCreate() {
		super.onCreate();
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);
		
//		getScreenTime();
		
		instance = this;
		
		// TODO 获取全局的上下文
		context = getApplicationContext();
		// 初始化ImageLoader
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
		
		
		getDensity();
		
	}
	
	public static  int width;
	public static  int height;
	public static  float density;
	public static  int densityDpi;
	/**
	 * 根据构造函数获得当前手机的屏幕系数
	 */
	public void getDensity() {
		// 获取当前屏幕
		DisplayMetrics dm = new DisplayMetrics();
		dm = getApplicationContext().getResources().getDisplayMetrics();

		width = dm.widthPixels;
		height = dm.heightPixels;
		density = dm.density;
		densityDpi = dm.densityDpi;
		
	}
	
	private void getScreenTime() {
		try {
			time = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
			Log.i("liuz", "time...." + time + "...period...." + period);
			
			if (time > period) {
				startTimer();
			}
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	
	public void startTimer() {
		startTimer(1000);
	}
	
	public void startTimer(int period) {
		if (mTimer == null) {
			mTimer = new Timer(true);
		}
		
		if (mTimer != null) {
			if (mTimerTask != null) {
				mTimerTask.cancel(); // 将原任务从队列中移除
			}
			mTimerTask = new MyTimerTask(); // 新建一个任务
			mTimer.scheduleAtFixedRate(mTimerTask, 0, period);
		}
		
		isStart = true;
		
	}
	
	public void stopTimer(){
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
		
		touchTime = 0;
		isStart = false;
	}
	
	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			touchTime += 1000;
			Log.i("liuz", "锁屏..." + touchTime);
			
			if (touchTime == 5000) {
				touchTimeDeal();
			}
		}
	}
	
	public void touchTimeDeal() {
		stopTimer();
		
		Intent intent = new Intent(getBaseContext(), ScreenPwdAct.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		getBaseContext().startActivity(intent);
	}

	public void addActivity(Activity activity){
		activityList.add(activity);
	}
	
	public void exitApplication(){
		try {
			for(Activity activity : activityList){
				if (activity != null){
					activity.finish();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.exit(0);
		}
	}
	
}
