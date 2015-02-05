package com.example.androidtest;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.androidtest.main.BaseActivity;

public class SpannableActivity extends BaseActivity {
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.spannable_layout);
		
		textView = (TextView) findViewById(R.id.textView1);
		
		SpannableString str = new SpannableString("阿隆索的方法看见说的话");
        str.setSpan(new ForegroundColorSpan(Color.RED), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(str);
//		textView.setMovementMethod(LinkMovementMethod.getInstance()); // 设置可点击
	}
}
