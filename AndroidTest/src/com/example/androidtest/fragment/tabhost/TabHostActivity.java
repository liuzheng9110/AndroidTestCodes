package com.example.androidtest.fragment.tabhost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidtest.main.BaseActivity;

public class TabHostActivity extends BaseActivity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		
		Button button01 = new Button(this);
		button01.setId(1);
		button01.setText("FragmentTabHost");
		button01.setOnClickListener(this);
		
		Button button02 = new Button(this);
		button02.setId(2);
		button02.setText("QQZoneTabHost");
		button02.setOnClickListener(this);
		
		layout.addView(button01);
		layout.addView(button02);
		
		setContentView(layout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 1:
			startActivity(new Intent(this, FragmentTabHostActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, QQZoneTabHostActivity.class));
			break;
		default:
			break;
		}
	}
}
