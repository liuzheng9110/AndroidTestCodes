package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 *  Class Name: CusAnimationAct.java
 *  Function:Ìø×ª¶¯»­
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-11-24 ÏÂÎç5:13:55
 *  @Copyright http://liuz.me
 */
public class CusAnimationAct extends Activity {
	
	private TextView textView;
//	private Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cus_animation_layout);
		
		textView = (TextView) findViewById(R.id.content);
		textView.setText(".........................");
	}
	
	public void click_listener(View view) {
		switch (view.getId()) {
		case R.id.pre_btn:
			
			textView.setText(R.string.long_text_1);
			
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
			textView.setAnimation(animation);
			
			break;
		case R.id.next_btn:
			
			textView.setText(R.string.long_text_2);
			
			break;
		}
	}
}
