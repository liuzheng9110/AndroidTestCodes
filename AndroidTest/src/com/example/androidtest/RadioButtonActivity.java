package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.example.androidtest.main.BaseActivity;

/**
*
* http://www.cnblogs.com/wt616/archive/2011/06/20/2085531.html
*/
public class RadioButtonActivity extends BaseActivity implements OnClickListener {
	
	private RadioButton radioButton1, radioButton2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.radio_button_layout);
		
		radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
		radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
		
		radioButton1.setOnClickListener(this);
		radioButton2.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		
	}
}
