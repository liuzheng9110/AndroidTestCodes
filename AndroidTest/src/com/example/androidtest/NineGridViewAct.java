package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class NineGridViewAct extends Activity {

	private GridView gridView;

	private ArrayList<String> itemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nine_gridview);
		
		itemList = new ArrayList<String>();
		itemList.add("我的待办");
		itemList.add("公文管理");
		itemList.add("收入计划");
		itemList.add("人事管理");
		itemList.add("法律法规");
		itemList.add("登记户查询");
		itemList.add("发票查询");
		itemList.add("工作任务");
		itemList.add("12366短信");
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new MyGridViewAdapter(this, itemList));

	}

	public class MyGridViewAdapter extends BaseAdapter{
		private Context mContext;
		private LayoutInflater mInflater;
		private ArrayList<String> mItemList;
		
		public MyGridViewAdapter(Context context, ArrayList<String> itemList) {
			this.mContext = context;
			this.mInflater = LayoutInflater.from(mContext); 
			this.mItemList = itemList;
		}
		
		@Override
		public int getCount() {
			return mItemList.size();
		}

		@Override
		public Object getItem(int position) {
			return mItemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final MyHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.nine_gridview_item, null);
				holder = new MyHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.nine_grid_view_img);
				holder.textView = (TextView) convertView.findViewById(R.id.nine_grid_view_text);
				
				convertView.setTag(holder);
			}else {
				holder = (MyHolder) convertView.getTag();
			}
			
			String string = mItemList.get(position);
			holder.textView.setText(string);
			
			return convertView;
		}
		
		class MyHolder{
			ImageView imageView;
			TextView textView;
		}
		
	}
	

}
