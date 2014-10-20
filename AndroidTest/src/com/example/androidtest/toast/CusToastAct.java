package com.example.androidtest.toast;

import com.example.androidtest.R;
import com.example.androidtest.R.id;
import com.example.androidtest.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CusToastAct extends Activity {
	
	private EditText editText;
	private Button button1, button2;
	private CusToast mCusToast;
	
	private Button button;
	
	private int time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cus_toast_layout);
		
		editText = (EditText) findViewById(R.id.editText1);
		button1 = (Button) findViewById(R.id.button1);
		
		button = (Button) findViewById(R.id.button);
		
		//  method 1
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null!=mCusToast) {
					mCusToast.hide();
				}
				time = TextUtils.isEmpty(editText.getText().toString())?CusToast.LENGTH_SHORT:Integer.valueOf(editText.getText().toString());
				mCusToast = CusToast.makeText(getApplicationContext(), "cus toast time...", time);
				mCusToast.show();
			}
		});
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomToast customToast = new CustomToast(getApplicationContext());
				customToast.setText("cus toast time...");
				customToast.showToast(10000);
			}
		});
	}
}
