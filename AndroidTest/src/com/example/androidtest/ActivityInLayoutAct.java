package com.example.androidtest;

import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressWarnings("deprecation")
public class ActivityInLayoutAct extends ActivityGroup {

	private LinearLayout layout;
	private Button button1, button2;

	/**
	 * 子View管理
	 */
	private Map<String, View> childViews = new HashMap<String, View>();
	private String currentTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.act_in_layout);

		layout = (LinearLayout) findViewById(R.id.act_layout);
		button1 = (Button) findViewById(R.id.aa);
		button2 = (Button) findViewById(R.id.bb);
		
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity("aa", new Intent(ActivityInLayoutAct.this, TextScaleAct.class));
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity("bb", new Intent(ActivityInLayoutAct.this, CusDatePickerAct.class));
			}
		});
		
		setApn();
		
	}
	
	/** 
     * 加载子Activity 
     *  
     * @param tag 
     * @param intent 
     */  
    private void startActivity(String tag, Intent intent) {  
        if (currentTag != null) {  
            View currentView = childViews.get(currentTag);  
            if (currentView != null)  
                currentView.setVisibility(View.GONE);  
        }  
        currentTag = tag;  
        View originView = childViews.get(tag);  
        final Window window = getLocalActivityManager().startActivity(tag, intent);  
        final View decorView = window.getDecorView();  
        if (decorView != originView && originView != null) {  
            if (originView.getParent() != null)  
                ((ViewGroup) originView.getParent()).removeView(originView);  
        }  
        childViews.put(tag, decorView);  
        if (decorView != null) {  
            decorView.setVisibility(View.VISIBLE);  
            decorView.setFocusableInTouchMode(true);  
            ((ViewGroup) decorView)  
                    .setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);  
            if (decorView.getParent() == null) {  
                layout.addView(decorView,  
                        new LinearLayout.LayoutParams(  
                                ViewGroup.LayoutParams.MATCH_PARENT,  
                                ViewGroup.LayoutParams.MATCH_PARENT));  
            }  
            decorView.requestFocus();  
        }  
    }  
    
    private void setApn() {
		Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);
//		ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.ApnSettings");
//		intent.setComponent(cm);
//		intent.setAction("android.intent.action.VIEW");
//		startActivity(intent);
		startActivity("gggg", intent);
	}
}
