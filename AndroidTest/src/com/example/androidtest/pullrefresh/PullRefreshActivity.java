package com.example.androidtest.pullrefresh;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.pullrefresh.widget.PullRefreshLayout;
import com.example.androidtest.pullrefresh.widget.PullRefreshLayout.OnRefreshListener;

/**
 * 
 *  Class Name: PullRefreshActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-26 ÏÂÎç3:09:07
 *  @Copyright http://liuz.me
 */
public class PullRefreshActivity extends BaseActivity implements OnRefreshListener {
	
	private PullRefreshLayout refreshLayout;
	private ListView listView;
	private ArrayList<String> datas = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		for (int i = 0; i < 20; i++) {
			datas.add("PullRefresh..." + (i+1));
		}
		
		setContentView(R.layout.pull_fresh_layout);
		
		refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		refreshLayout.setOnRefreshListener(this);
		
		listView = (ListView) findViewById(R.id.listView);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
		listView.setAdapter(adapter);
		
	}
	
	@Override
	public void onRefresh() {
		refreshLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				refreshLayout.setRefreshing(false);

				datas.add("aaaaa" + 1);
				datas.add("aaaaa" + 2);
				datas.add("aaaaa" + 3);
				
				adapter.notifyDataSetChanged();
			}
		}, 3000);
	}
}
