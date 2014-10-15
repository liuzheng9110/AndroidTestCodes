package com.example.androidtest.sliding;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;

public class SlidingPaneLayoutAct extends Activity {
	
	private SlidingPaneLayout slidingPaneLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sliding_panel_layout);
		
		slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_lay);
		
		
		
		
	}
}
