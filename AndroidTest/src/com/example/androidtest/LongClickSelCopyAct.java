package com.example.androidtest;

import android.app.Activity;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

/**
 * 长按选择复制
 */
public class LongClickSelCopyAct extends Activity {
	
	private TextView textView;
	private MyCopyTextView textView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.long_click_copy);
		
		textView = (TextView) findViewById(R.id.textView1);
		textView.setText("谷歌I/O大会开始了，首先播放的是一段视频，现场的车辆座椅和摄像头等装置，看来车载功能是没跑了。" +
				"谷歌I/O大会开始了，首先播放的是一段视频，现场的车辆座椅和摄像头等装置，看来车载功能是没跑了。");
		
		
		textView2 = (MyCopyTextView) findViewById(R.id.myCopyTextView1);
		textView2.setText("苹果升级16GB版第五代iPod Touch，新增iSight摄像头，售价199美元 | 苹果今天推出了升级版的16GB版本的第五代iPod Touch，新增500像素iSight摄像头，售价199美元。" +
				"苹果升级16GB版第五代iPod Touch，新增iSight摄像头，售价199美元 | 苹果今天推出了升级版的16GB版本的第五代iPod Touch，新增500像素iSight摄像头，售价199美元。");
		
		
		
	}
	
}
