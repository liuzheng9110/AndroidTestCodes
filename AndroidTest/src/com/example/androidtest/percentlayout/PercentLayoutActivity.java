package com.example.androidtest.percentlayout;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;

public class PercentLayoutActivity extends Activity{
	
	private PercentRelativeLayout prLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.percent_layout);
		
		
	}
}
