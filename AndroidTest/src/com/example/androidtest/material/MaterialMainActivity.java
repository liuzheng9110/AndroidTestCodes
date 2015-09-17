package com.example.androidtest.material;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.androidtest.R;

/**
 * 
 *  Class Name: MaterialMainActivity.java
 *  Function: 
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年6月23日 上午11:15:00
 *  @Copyright http://liuz.me 
 *  @url
 */
public class MaterialMainActivity extends AppCompatActivity {
	
	private Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.material_layout);
		
		toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		setSupportActionBar(toolbar);
		
		
	}
}
