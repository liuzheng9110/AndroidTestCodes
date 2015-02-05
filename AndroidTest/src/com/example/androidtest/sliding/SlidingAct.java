package com.example.androidtest.sliding;

import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.sliding.residemenu.SlidingResideMenuAct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SlidingAct extends BaseActivity implements OnClickListener {
	
	public final static int TYPE_SLIDING_MENU = 1;
	public final static int TYPE_SLIDING_DRAWER = 2;
//	public final static int TYPE_SLIDING_MENU = 3;
//	public final static int TYPE_SLIDING_MENU = 4;
//	public final static int TYPE_SLIDING_MENU = 5;
//	public final static int TYPE_SLIDING_MENU = 6;
	
	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("SlidingMenu Demo");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		
		Button slidingMenuBtn = new Button(this);
		slidingMenuBtn.setId(1);
		slidingMenuBtn.setText("SlidingMenu");
		slidingMenuBtn.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		slidingMenuBtn.setOnClickListener(this);
		
		Button slidingDrawerBtn = new Button(this);
		slidingDrawerBtn.setId(2);
		slidingDrawerBtn.setText("SlidingDrawer");
		slidingDrawerBtn.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		slidingDrawerBtn.setOnClickListener(this);
		
		Button slidingPaneLayoutBtn = new Button(this);
		slidingPaneLayoutBtn.setId(3);
		slidingPaneLayoutBtn.setText("SlidingPaneLayout");
		slidingPaneLayoutBtn.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		slidingPaneLayoutBtn.setOnClickListener(this);
		
		Button viewDragHelperBtn = new Button(this);
		viewDragHelperBtn.setId(4);
		viewDragHelperBtn.setText("ViewDragHelper");
		viewDragHelperBtn.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		viewDragHelperBtn.setOnClickListener(this);
		
		Button resideMenuBtn = new Button(this);
		resideMenuBtn.setId(5);
		resideMenuBtn.setText("ResideMenu");
		resideMenuBtn.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		resideMenuBtn.setOnClickListener(this);
		
		layout.addView(slidingMenuBtn);
		layout.addView(slidingDrawerBtn);
		layout.addView(slidingPaneLayoutBtn);
		layout.addView(viewDragHelperBtn);
		layout.addView(resideMenuBtn);
		
		setContentView(layout);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 1:
			startAct(SlidingMenuAct.class);
			break;
		case 2:
			startAct(SlidingDrawerAct.class);
			break;
		case 3:
			startAct(SlidingPaneLayoutAct.class);
			break;
		case 4:
			startAct(SlidingViewDragHelperAct.class);
			break;
		case 5:
			startAct(SlidingResideMenuAct.class);
			break;
		default:
			break;
		}
	}
	
//	private void showShortToast(String msg){
//		if (mToast==null) {
//			mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
//		}else {
//			mToast.setText(msg);
//			mToast.setDuration(Toast.LENGTH_SHORT);
//		}
//		mToast.show();
//	}
	
	private void startAct(Class<?> cls){
		startActivity(new Intent(this, cls));
	}
}
