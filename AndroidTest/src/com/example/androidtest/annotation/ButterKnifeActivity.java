package com.example.androidtest.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.FindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;
import butterknife.ResourceString;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: ButterKnifeActivity.java
 *  Function:ButterKnife 简单使用
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年4月15日 上午9:01:44
 *  @Copyright http://liuz.me 
 *  @url http://stormzhang.com/openandroid/android/2014/01/12/android-butterknife/ 
 *  	 http://jakewharton.github.io/butterknife/
 *  	 http://jakewharton.github.io/butterknife/ide-eclipse.html ButterKnife eclipse 配置
 */
public class ButterKnifeActivity extends BaseActivity {
	// 控件初始化
	@FindView(R.id.text_view) TextView textView;
	@FindView(R.id.editText) EditText editText;
	@FindView(R.id.button) Button button;
	@FindView(R.id.list_view) ListView listView;
	
	// 资源初始化 
	@ResourceString(R.string.app_name) String textString;
	
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.butter_knife_layout);
		// 引入 ButterKnife 注解
		ButterKnife.bind(this);

		textView.setText("ButterKnife TextView");
		editText.setText(textString);
		button.setText("ButterKnife Button");
		
		adapter = new SimpleAdapter(this);
		listView.setAdapter(adapter);
		
	}
	
	// 按钮单击
	@OnClick(R.id.button)
	void btnClick() {
		showShortToast("butter knife inject button click listener");
	}
	
	// 按钮长按
	@OnLongClick(R.id.button)
	boolean onLongClick(){
		showShortToast("ButterKnife Long Click ...");
		return true;
	}
	
	// list item 单击
	@OnItemClick(R.id.list_view)
	void onListItemClick(int position){
		showShortToast("position..." + adapter.getItem(position).toString());
	}
	
	// list item 长按
	@OnItemLongClick(R.id.list_view)
	boolean onListLongClick(){
		showShortToast("ButterKnife List Long Click ...");
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 销毁
		ButterKnife.unbind(this);
	}
	
}
