package com.example.androidtest.update;

import com.example.androidtest.main.MyApplication;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AndroidUpdateService extends Service {
	
	private String filePath, saveName, fileUrl;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("liuz", "onStartCommand..........download...");
		if (intent!=null) {
			filePath = intent.getStringExtra("filePath");
			saveName = intent.getStringExtra("saveName");
			fileUrl = intent.getStringExtra("fileUrl");
		}
		
		new Thread(){
			@Override
			public void run() {
				Log.i("liuz", "fileUrl..." + fileUrl);
				Log.i("liuz", "filepath..." + filePath);
				Log.i("liuz", "saveName..." + saveName);
				
				UpdateManager.download();
				
				MyApplication.getInstance().stopService(new Intent(MyApplication.getInstance(), AndroidUpdateService.class));
			}
		}.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("liuz", "onDestroy......");
		super.onDestroy();
	}
}
