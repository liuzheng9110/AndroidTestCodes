package com.example.androidtest.main;

import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	private static MyApplication instance;
	private List<Activity> activityList;
	
	public MyApplication() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);
		
		instance = this;
	}
	
	public static MyApplication getInstance(){
		if(instance == null){
			instance = new MyApplication();
		}
		return instance;
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
