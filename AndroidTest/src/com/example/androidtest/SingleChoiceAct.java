package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SingleChoiceAct extends Activity {
	
	private ListView listView;
//	private ArrayAdapter<String> adapter;
	private SingleChoiceAdapter adapter;
	private ArrayList<String> dataList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_choice_listview);
		
		dataList.add("11111111");
		dataList.add("22222222");
		dataList.add("33333333");
		dataList.add("44444444");
		dataList.add("55555555");
		dataList.add("66666666");
		
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataList);
//		adapter = new ArrayAdapter<String>(this, R.layout.single_choice_listview_item, dataList);
		
		adapter = new SingleChoiceAdapter(this, dataList);
		
		listView = (ListView) findViewById(R.id.single_choice_listview);
		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				listView.setItemChecked(position, true);
			}
		});
		
//		listView.setItemsCanFocus(false);
		
	}
}
