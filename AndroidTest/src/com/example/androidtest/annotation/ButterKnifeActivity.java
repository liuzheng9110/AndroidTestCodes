package com.example.androidtest.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;


/**
 * 
 *  Class Name: ButterKnifeActivity.java
 *  Function:ButterKnife ��ʹ��
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015��4��15�� ����9:01:44
 *  @Copyright http://liuz.me 
 *  @url http://stormzhang.com/openandroid/android/2014/01/12/android-butterknife/ 
 *  	 http://jakewharton.github.io/butterknife/
 *  	 http://jakewharton.github.io/butterknife/ide-eclipse.html ButterKnife eclipse ����
 */
public class ButterKnifeActivity extends BaseActivity {
	
	// �ؼ���ʼ��
	@Bind(R.id.text_view) TextView textView;
	@Bind(R.id.editText) EditText editText;
	@Bind(R.id.button) Button button;
	@Bind(R.id.list_view) ListView listView;
	
	// ��Դ��ʼ�� 
	@BindString (R.string.app_name) String textString;
	
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.butter_knife_layout);
		// ���� ButterKnife ע��
		ButterKnife.bind(this);

		textView.setText("ButterKnife TextView");
		editText.setText(textString);
		button.setText("ButterKnife Button");
		
		adapter = new SimpleAdapter(this);
		listView.setAdapter(adapter);
		
	}
	
	// ��ť����
	@OnClick(R.id.button)
	void btnClick() {
		showShortToast("butter knife inject button click listener");
	}
	
	// ��ť����
	@OnLongClick(R.id.button)
	boolean onLongClick(){
		showShortToast("ButterKnife Long Click ...");
		return true;
	}
	
	// list item ����
	@OnItemClick(R.id.list_view)
	void onListItemClick(int position){
		showShortToast("position..." + adapter.getItem(position).toString());
	}
	
	// list item ����
	@OnItemLongClick(R.id.list_view)
	boolean onListLongClick(){
		showShortToast("ButterKnife List Long Click ...");
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// ����
		ButterKnife.unbind(this);
	}
	
}
