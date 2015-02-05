package com.example.androidtest.a_foresee.gridview;

import java.util.ArrayList;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: TaxperGridActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015-1-8 ÉÏÎç10:43:26
 *  @Copyright http://liuz.me
 */
public class TaxperGridActivity extends BaseActivity {
	
	private GridView gridView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> arrayList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taxper_grid_layout);
		
		arrayList = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			arrayList.add(""+i);
		}
		
		gridView = (GridView) findViewById(R.id.taxper_gridview);
		adapter = new ArrayAdapter<String>(this, R.layout.taxper_grid_layout_item, arrayList);
		gridView.setAdapter(adapter);
		
		
	}
}
