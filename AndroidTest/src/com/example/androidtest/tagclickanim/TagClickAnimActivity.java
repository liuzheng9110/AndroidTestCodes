package com.example.androidtest.tagclickanim;

import android.os.Bundle;
import android.widget.GridView;

import com.example.androidtest.R;
import com.example.androidtest.draglistview.DragSortGridView;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: TagClickAnimActivity.java
 *  Function: 点击 tag 动画
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年4月22日 下午3:12:15
 *  @Copyright http://liuz.me 
 *  @url 
 */
public class TagClickAnimActivity extends BaseActivity {
	
//	private DragGridView dGridView;
	private OtherGridView oGridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tag_click_anim_layout);
		
//		dGridView = (DragGridView) findViewById(R.id.dragSortGridView);
		oGridView = (OtherGridView) findViewById(R.id.other_gridview);
		
		
		
	}
}
