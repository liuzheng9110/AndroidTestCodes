package com.example.androidtest.wheelview;

import java.lang.reflect.Array;
import java.util.Arrays;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.wheelview.WheelView.OnWheelViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 
 *  Class Name: WheelviewActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月22日 上午11:30:56
 *  @Copyright http://liuz.me 
 *  @url https://github.com/wangjiegulu/WheelView 
 */
public class WheelviewActivity extends BaseActivity {
	
	private WheelView wheelView;
	private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wheel_view_layout);
	
		wheelView = (WheelView) findViewById(R.id.wheelView1);
		wheelView.setOffset(1);
		wheelView.setItems(Arrays.asList(PLANETS));
		wheelView.setOnWheelViewListener(new OnWheelViewListener(){
			@Override
			public void onSelected(int selectedIndex, String item) {
				showShortToast("selectedIndex..." + selectedIndex + "...item..." + item);
			}
		});
	}
	
	@Override
	public void click_listener(View v) {
		View view = LayoutInflater.from(this).inflate(R.layout.wheel_view, null, false);
		WheelView wheelView = (WheelView) view.findViewById(R.id.wheelView);
		wheelView.setOffset(2);
		wheelView.setItems(Arrays.asList(PLANETS));
		wheelView.setSeletion(4);
		wheelView.setOnWheelViewListener(new OnWheelViewListener(){
			@Override
			public void onSelected(int selectedIndex, String item) {
				showShortToast("selectedIndex..." + selectedIndex + "...item..." + item);
			}
		});
		
		new AlertDialog.Builder(this)
			.setTitle("Wheelview in dialog")
			.setView(view)
			.setPositiveButton("OK", null)
			.show();
		
	}
}