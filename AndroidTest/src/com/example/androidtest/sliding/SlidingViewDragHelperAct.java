package com.example.androidtest.sliding;

import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

public class SlidingViewDragHelperAct extends BaseActivity {
	
	private ViewDragHelper viewDragHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sliding_viewdraghelper_layout);
		
	}
}
