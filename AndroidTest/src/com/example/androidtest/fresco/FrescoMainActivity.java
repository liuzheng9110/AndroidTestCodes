package com.example.androidtest.fresco;

import android.net.Uri;
import android.os.Bundle;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 
 *  Class Name: FrescoMainActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年5月13日 下午2:44:24
 *  @Copyright http://liuz.me 
 *  @url https://github.com/facebook/fresco/  & fresco-cn.org/ & fresco-cn.org/docs/
 */
public class FrescoMainActivity extends BaseActivity {
	
	SimpleDraweeView draweeView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Fresco.initialize(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fresco_main_layout);
		
		draweeView = (SimpleDraweeView) findViewById(R.id.simple_drawee_view);
		draweeView.setAlpha(5f);
		draweeView.setImageURI(Uri.parse("https://ss0.bdstatic.com/5a21bjqh_Q23odCf/static/superplus/img/logo_white.png"));
		
	}
}
