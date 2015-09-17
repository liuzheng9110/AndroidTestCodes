package com.example.androidtest.snackbar;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 *  Class Name: SnackbarActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月13日 下午12:07:39
 *  @Copyright http://liuz.me 
 *  @url
 */
public class SnackbarActivity extends Activity implements OnClickListener {
	
	private Button snackBtn;
	private CoordinatorLayout coordinatorLayout;
	private Snackbar snackbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.snackbar_layout);
		// 
		snackBtn = (Button) findViewById(R.id.snackbar_btn);
		snackBtn.setOnClickListener(this);
		// 
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coor_layout);
		
	}

	@Override
	public void onClick(View v) {
		// 
		snackbar = Snackbar.make(coordinatorLayout, "snackbar", Snackbar.LENGTH_SHORT);
		// snackbar.show();
		
		snackbar.setAction("SetText", new OnClickListener() {
			@Override
			public void onClick(View v) {
				snackbar.dismiss();
			}
		}).show();
	}
}
