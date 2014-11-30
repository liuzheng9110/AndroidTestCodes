package com.example.androidtest.autofit;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidtest.R;

/**
 * 
 *  Class Name: AutoFitTextViewAct.java
 *  Function:自适应文字大小 textview
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-11-24 下午2:30:27
 *  @Copyright 方欣科技湖南分公司
 *  @Via  https://github.com/grantland/android-autofittextview
 */
public class AutoFitTextViewAct extends Activity {
	
	private TextView textView, autoFitTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto_fit_textview_layout);
		
		textView = (TextView) findViewById(R.id.simple_textview);
		autoFitTextView = (TextView) findViewById(R.id.autofit_textview);
		
		((EditText)findViewById(R.id.autofit_edittext)).addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				textView.setText(s);
				autoFitTextView.setText(s);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});	
		
	}
}
