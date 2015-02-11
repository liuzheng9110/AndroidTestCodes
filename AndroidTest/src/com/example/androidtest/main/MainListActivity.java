package com.example.androidtest.main;

import com.example.androidtest.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 *  Class Name: MainListActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年2月11日 上午8:27:42
 *  @Copyright http://liuz.me
 */
public class MainListActivity extends BaseActivity {
	
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private String[] data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list_layout);
		
		data = getResources().getStringArray(R.array.main_list_array);
		
		listView = (ListView) findViewById(R.id.main_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);;
		
	}
}
