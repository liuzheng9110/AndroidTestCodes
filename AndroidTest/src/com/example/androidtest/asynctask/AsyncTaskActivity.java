package com.example.androidtest.asynctask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.util.Utils;

/**
 * 
 *  Class Name: AsyncTaskActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月3日 下午7:58:31
 *  @Copyright http://liuz.me 
 *  @url
 */
public class AsyncTaskActivity extends BaseActivity {
	private Button button;
	private TextView textView;
	private MyTask myTask;
	
	private final static String JSON_URL = "https://raw.githubusercontent.com/liuzheng9110/liuzheng9110.github.io/master/project_json/feedback.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_task_layout);
		
		button = (Button) findViewById(R.id.btn);
		textView = (TextView) findViewById(R.id.async_result);

		myTask = new MyTask();
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myTask.execute(JSON_URL);
			}
		});
	}
	
	private class MyTask extends AsyncTask<String, Integer, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			textView.setText("loading...");
		}

		@Override
		protected String doInBackground(String... params) {
			HttpURLConnection urlConnection = null;
			try {
				URL url = new URL(params[0]);
				
				urlConnection = (HttpURLConnection) url.openConnection();
//				urlConnection.setDoOutput(true);
//				urlConnection.setDoInput(true);
//				urlConnection.setRequestProperty("Content-type", "application/json");
				urlConnection.connect();
				
				// 
				InputStream is = urlConnection.getInputStream();
				return Utils.inStream2String(is);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				urlConnection.disconnect(); // 关闭链接
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			textView.setText(result);
		}
	}
}
