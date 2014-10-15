package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 *   自定义edittext
 *	http://androidopentutorials.com/android-vertical-scrollbar-styling/
 */
public class CusEditTextAct extends Activity implements OnClickListener {
	private EditText editText01, editText02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.cus_edittext_layout_1);
		setContentView(R.layout.cus_edittext_layout);
		
		((Button) findViewById(R.id.show_edittext_1)).setOnClickListener(this);
		((Button) findViewById(R.id.show_edittext_2)).setOnClickListener(this);
		
		editText01 = (EditText) findViewById(R.id.cus_edittext_1);
		editText02 = (EditText) findViewById(R.id.cus_edittext_2);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.show_edittext_1:
			editText02.setVisibility(View.GONE);
			editText01.setVisibility(View.VISIBLE);
			break;
		case R.id.show_edittext_2:
			editText01.setVisibility(View.GONE);
			editText02.setVisibility(View.VISIBLE);
			break;
		}
	}
}
