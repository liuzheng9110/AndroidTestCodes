package com.example.androidtest.database;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.sqlite.ormlite.UserData;

/**
 * 
 *  Class Name: DataBaseActivity.java
 *  Function: 
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月25日 上午10:05:42
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DataBaseActivity extends BaseActivity {
	
	private ListView listView;
	private LayoutInflater layoutInflater;
	private ArrayList<UserInfo> userDatas = new ArrayList<UserInfo>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_base_layout);
		
		userDatas = DBUtil.getInstance().getUserInfos();
		if (userDatas.size() <= 0) {
			for (int i = 0; i < 5; i++) {
				UserInfo userInfo = new UserInfo(i + "", "aaa..." + i);
				userDatas.add(userInfo);
			}
			
			DBUtil.getInstance().addUserInfo(userDatas);
			userDatas = DBUtil.getInstance().getUserInfos();
		}
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new MyAdapter());
		layoutInflater = LayoutInflater.from(this);
	}
	
	class MyAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userDatas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return userDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = layoutInflater.inflate(android.R.layout.simple_list_item_2, null);
			TextView id = (TextView) convertView.findViewById(android.R.id.text1);
			TextView name = (TextView) convertView.findViewById(android.R.id.text2);
			
			UserInfo userInfo = userDatas.get(position);
			id.setText(userInfo.getUserId());
			name.setText(userInfo.getUserName());
			return convertView;
		}
	}
	
	
}
