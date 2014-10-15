package com.example.androidtest;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;

public class MultAnimateAct extends Activity {
	
	private Button btn01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mult_animate);
		
		btn01 = (Button) findViewById(R.id.animate_btn_01);
		
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Animation animation = AnimationUtils.loadAnimation(MultAnimateAct.this, R.anim.animate_01);
//				btn01.startAnimation(animation);
				
				AnimatorSet as = new AnimatorSet();
				as.playTogether(ObjectAnimator.ofFloat(v, "rotationY", 90, 0),
						ObjectAnimator.ofFloat(v, "translationX", -330, 0).setDuration(700),
		                ObjectAnimator.ofFloat(v, "alpha", 0, 1).setDuration(1400*3/2));
				as.start();
			}
		});
	}
	
    public void reset(View view) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth() / 2.0f);
        ViewHelper.setPivotY(view, view.getMeasuredHeight() / 2.0f);
    }
}
