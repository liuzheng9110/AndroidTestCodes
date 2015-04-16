package com.example.androidtest.webservice;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.androidtest.main.BaseActivity;

public class MainWebserviceActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);	
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		Button button = new Button(this);
		button.setId(1);
		button.setText("webservice test");
		button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShortToast(".........");
			}
		});

		layout.addView(button);
		setContentView(layout);
	}
}
