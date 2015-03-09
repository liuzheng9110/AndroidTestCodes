package com.example.androidtest.recycle_cardview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.androidtest.main.BaseActivity;

public class RecyclerCardViewActivity extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		
		Button recycleBtn = new Button(this);
		recycleBtn.setId(1001);
		recycleBtn.setText("RecycleView");
		recycleBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		recycleBtn.setOnClickListener(this);
		
		Button cardBtn = new Button(this);
		cardBtn.setId(1002);
		cardBtn.setText("CardView");
		cardBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		cardBtn.setOnClickListener(this);
		
		layout.addView(recycleBtn);
		
		setContentView(layout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 1001:
			startActivity(new Intent(this, RecyclerViewAct.class));
			break;
		case 1002:
			startActivity(new Intent(this, CardViewAct.class));
			break;
		default:
			break;
		}
	}
}
