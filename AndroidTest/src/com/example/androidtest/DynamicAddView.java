package com.example.androidtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicAddView extends Activity {
	
	private LinearLayout layout;
	private Button addBtn;
	
	private LayoutInflater inflater;
	private int  i = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_add_view);
		
		inflater = LayoutInflater.from(this);
		layout = (LinearLayout) findViewById(R.id.dynamic_lay);
		
		addBtn = (Button) findViewById(R.id.add_btn);
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layout.addView(createDynamicView());
			}
		});
		
//		dynamicView();
	}

	private View createDynamicView(){
		int id = i++;
		View v = inflater.inflate(R.layout.add_view, layout, false);
		v.setId(id);
		((TextView) v.findViewById(R.id.editText1)).setText(""+id);
		final ImageView iv = (ImageView) v.findViewById(R.id.imageView2);
		iv.setTag(id);
		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int index = (Integer) iv.getTag();
				layout.removeView(layout.findViewById(index));
			}
		});
		
		return v;
	}
	
	private void dynamicView() {
		
		ArrayList<String> hangYeList = new ArrayList<String>();
		try {
			InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open("shuizhong.txt"));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			while ((line = bufReader.readLine()) != null){
				hangYeList.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < hangYeList.size(); i++) {
			View view = inflater.inflate(R.layout.dynamic_add_view_item, null);
			
			TextView tv1_1 = (TextView) view.findViewById(R.id.tv1_1);
			TextView tv1_2 = (TextView) view.findViewById(R.id.tv1_2);

			tv1_1.setText(hangYeList.get(i));
			
			layout.addView(view);
		}
	}
}
