package com.example.androidtest.jsonparse;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.helper.StringUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.util.Utils;
import com.google.zxing.common.StringUtils;

/**
 * 
 *  Class Name: MultiJsonParseActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月3日 上午9:31:48
 *  @Copyright http://liuz.me 
 *  @url  http://repo1.maven.org/maven2/com/alibaba/fastjson/
 */
public class MultiJsonParseActivity extends BaseActivity {
	
	private final static String JSON_URL = "https://raw.githubusercontent.com/liuzheng9110/liuzheng9110.github.io/master/project_json/feedback.json";
	private MyTask mTask;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multi_json_parse_layout);
		
		textView = (TextView) findViewById(R.id.json_text);
		
		mTask = new MyTask();
		mTask.execute(JSON_URL);
	}
	
	private class MyTask extends AsyncTask<String, Integer, String>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			textView.setText("loading......");
		}
		
		@Override
		protected String doInBackground(String... params) {
			HttpURLConnection urlConnection = null;
			try {
				URL url = new URL(params[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.connect();
				
				InputStream inputStream = urlConnection.getInputStream();
				String aaa = Utils.inStream2String(inputStream);
				
//				Log.i("liuz", aaa);
				return aaa;
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				urlConnection.disconnect();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			textView.setText(result);
			
			parseJson(result);
		}
	}

	/**
	 * 
	 *  Function: parseJsonText
	 *  @author liuzheng
	 *  @created 2015年8月3日 上午10:41:32 
	 *  @param result
	 */
	public void parseJson(String result) {
		tiripPackage tiripPackage = JSON.parseObject(result, tiripPackage.class);
		Log.i("liuz", "..." + tiripPackage.getService());
	}
}
