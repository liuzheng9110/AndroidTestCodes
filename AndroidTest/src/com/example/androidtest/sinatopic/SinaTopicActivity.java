package com.example.androidtest.sinatopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidtest.R;
import com.example.androidtest.sinatopic.ScreenListener.ScreenStateListener;

/**
 * 
 *  Class Name: SinaTopicActivity.java
 *  Function: Sina Topic Demo
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月9日 上午10:27:06
 *  @Copyright http://liuz.me 
 *  @url https://github.com/simple-android-framework-exchange/the-fucking-traps-in-android-dev/blob/master/dev/views/%E4%BB%BF%E6%96%B0%E6%B5%AA%E5%BE%AE%E5%8D%9A%E5%8A%A0%EF%BC%83%E8%AF%9D%E9%A2%98%E7%9A%84EditText%E5%AE%9E%E7%8E%B0.md
 */
public class SinaTopicActivity extends Activity {
	
	private EditText editText;
	private Button btn;
	private ArrayList<TopicInfo> mList = new ArrayList<TopicInfo>();
	
	private ScreenListener screenListener;
	private int TEXT_CHANGE_LISTENER_FLAG = 0;
	private MyTextWatcher watcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sinatopic_layout);
		
		btn = (Button) findViewById(R.id.btn);
		editText = (EditText) findViewById(R.id.editText);

		watcher = new MyTextWatcher();
		
		initReciever();
		addListener();
	}
	
	private void initReciever() {
		screenListener = new ScreenListener(this);
		// 注册广播
		screenListener.begin(new ScreenStateListener() {

		    @Override	
		    public void onUserPresent() {
		        Log.e("liuz", "onUserPresent");
		    }

		    @Override
		    public void onScreenOn() {
		        Log.e("liuz", "onScreenOn");
		        if (TEXT_CHANGE_LISTENER_FLAG == 0) {
		            editText.addTextChangedListener(watcher);
		            TEXT_CHANGE_LISTENER_FLAG = 1;
		        }
		    }

		    @Override
		    public void onScreenOff() {
		        Log.e("liuz", "onScreenOff");
		    }
		});
	}

	private void addListener() {
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int nextInt = new Random().nextInt(100); // 生成随机数
				
				String str = "#仿新浪微博添加话题" + nextInt + "#";
				
				editText.setText(editText.getText());
				editText.append(str);
				editText.setSelection(editText.getText().toString().length());
				
				mList.add(new TopicInfo(nextInt+"", str));
			}
		});
		
		// 监听删除键事件
		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
					int selectionStart = editText.getSelectionStart(); // 起始位置
					int selectionEnd = 0; // 结束位置
					
					for (int i = 0; i < mList.size(); i++) { // 遍历输入框字符
						if ((selectionEnd = editText.getText().toString().indexOf(mList.get(i).getTopicName(), selectionEnd)) != -1) { // 判断最后位置
							if (selectionStart != 0 && selectionStart >= selectionEnd && selectionStart <= (selectionEnd + mList.get(i).getTopicName().length())) { // 确定起始位置
								String sss = editText.getText().toString();
								
								editText.setText(sss.substring(0, selectionEnd) + sss.substring(selectionEnd + mList.get(i).getTopicName().length()));  // 删掉符合条件的字符串
								
								mList.remove(i); // 移除集合中符合条件的数据
								editText.setSelection(selectionEnd); // 设置光标位置
								
								return true;
							}
						} else{ // 
							selectionEnd += ("#" + mList.get(i).getTopicName() + "#").length();
						}
					}
				}
				return false;
			}
		});
		
		editText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				int selectionStart = editText.getSelectionStart(); // 起始位置
				int selectionEnd = 0; // 结束位置
				
				for (int i = 0; i < mList.size(); i++) { // 遍历输入框字符
					if ((selectionEnd = editText.getText().toString().indexOf(mList.get(i).getTopicName(), selectionEnd)) != -1) { // 判断最后位置
						if (selectionStart >= selectionEnd && selectionStart <= (selectionEnd + mList.get(i).getTopicName().length())) { // 确定起始位置
							editText.setSelection(selectionEnd + mList.get(i).getTopicName().length()); // 设置光标位置
						}
					} else{ // 
						selectionEnd += ("#" + mList.get(i).getTopicName() + "#").length();
					}
				}
			}
		});
	}

	//////////////////////////////////////////////
	class MyTextWatcher implements TextWatcher {

		@Override
		public synchronized void afterTextChanged(Editable s) {
			editText.removeTextChangedListener(watcher);
			
			TEXT_CHANGE_LISTENER_FLAG = 0;
			int findPos = 0;
			int copyPos = 0;
			
			String text = s.toString(); // 获取 edittext 文本内容
			
			List<Integer> spanIndexs = new ArrayList<Integer>();
			s.clear();
			
			for (int i = 0; i < mList.size(); i++) {
				String tempName = "#" + mList.get(i).getTopicName() + "#";
				
				if ((findPos = text.indexOf(tempName, findPos)) != -1) { // 找到话题位置
					spanIndexs.add(findPos); // 
					spanIndexs.add(findPos + tempName.length());
				}
			}
			
			if (spanIndexs != null && spanIndexs.size() != 0) {
				for (int i = 0; i < spanIndexs.size(); i++) {
					if (i % 2 == 0) {
						s.append(text.substring(copyPos, spanIndexs.get(i)));
					}else{
						Spanned html = Html.fromHtml("<font color='blue'>" + text.substring(copyPos, spanIndexs.get(i)) + "</font>");
						s.append(html);
					}
					copyPos = spanIndexs.get(i);
				}
				s.append(text.substring(copyPos));
			}
			s.append(text);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 取消注册广播
		screenListener.unregisterListener();
	}
}
