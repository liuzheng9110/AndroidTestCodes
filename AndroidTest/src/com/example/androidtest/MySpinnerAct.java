package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MySpinnerAct extends Activity {
	
	private Button btn01, btn02;
	private Spinner spinner1;
	
	private ArrayList<String> dataList = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_spinner);
		
		btn01 = (Button) findViewById(R.id.button1);
		btn02 = (Button) findViewById(R.id.button2);
		
		btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				spinner1.setAdapter(adapter);
//				spinner2.setAdapter(adapter);
			}
		});
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dataList.clear();
				dataList.add("111");
				dataList.add("222");
				dataList.add("333");
				dataList.add("444");
				
				adapter.notifyDataSetChanged();
			}
		});
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);

		dataList.add("aaa");
		dataList.add("bbb");
		dataList.add("ccc");
		dataList.add("ddd");
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
	}
}
