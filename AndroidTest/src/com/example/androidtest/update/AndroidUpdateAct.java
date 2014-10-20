package com.example.androidtest.update;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidtest.R;
import com.example.androidtest.util.FileUtils;
import com.example.androidtest.util.Utils;

public class AndroidUpdateAct extends Activity {

	private Button button;
	private Context mContext;
	private LayoutInflater inflater;

	private String cachePath = FileUtils.getFilePath(mContext); 
	private File appFile;
	private String saveName, filePath;
	
	private final String fileUrl = "http://dldir1.qq.com/qqfile/QQIntl/QQi_wireless/Android/qqi_5.0.10.6043_android_office.apk";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_layout);

		mContext = this;
		inflater = LayoutInflater.from(mContext);
		
		saveName = FileUtils.getFileName(fileUrl);
		filePath = cachePath + "app/"+saveName;
		appFile = new File(filePath);

		button = (Button) findViewById(R.id.upd_btn);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Utils.showShortToast(mContext, "upd app ...");
				showUpdInfoDialog();
			}
		});
	}

	protected void showUpdInfoDialog() {
		final Dialog dialog = new Dialog(mContext, R.style.dialog_theme);

		View v = inflater.inflate(R.layout.update_dialog_layout, null);
		Button button1 = (Button) v.findViewById(R.id.update_now_btn);
		Button button2 = (Button) v.findViewById(R.id.next_statu_btn);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startDownload();
				dialog.dismiss();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(v);
		dialog.show();
	}

	protected void startDownload() {
		Utils.showShortToast(mContext, "start download app ...");
		Intent intent = new Intent(AndroidUpdateAct.this, AndroidUpdateService.class);
		intent.putExtra("fileUrl", fileUrl);
		intent.putExtra("filePath", filePath);
		intent.putExtra("saveName", saveName);
		startService(intent);

	}

}
