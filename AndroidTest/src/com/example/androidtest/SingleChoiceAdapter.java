package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

public class SingleChoiceAdapter extends BaseAdapter {
	
	private Activity mActivity;
	private ArrayList<String> dataList;
	private LayoutInflater mInflater;
	
	public SingleChoiceAdapter(Activity activity, ArrayList<String> dataList){
		this.mActivity = activity;
		this.dataList = dataList;
		
		mInflater = LayoutInflater.from(mActivity);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup parent) {
//		View view = mInflater.inflate(android.R.layout.simple_list_item_single_choice, parent, false);
		View view = mInflater.inflate(R.layout.single_choice_listview_item, parent, false);
		
		TextView tv = (TextView) view;
		
		tv.setText(dataList.get(position));
		return tv;
	}
	
//	@Override
//	public View getView(int position, View converView, ViewGroup parent) {
//		ViewHolder holder;
//		if (converView==null) {
//			converView = mInflater.inflate(android.R.layout.simple_list_item_single_choice, null);
//			holder = new ViewHolder();
//			
//			holder.myCheckedTextView = (CheckedTextView) converView.findViewById(R.id.single_choice_item);
//			
//			converView.setTag(holder);
//		}else {
//			holder = (ViewHolder) converView.getTag();
//		}
//		
//		holder.myCheckedTextView.setText(dataList.get(position));
//		boolean check = ((ListView)parent).isItemChecked(position);  
//		holder.myCheckedTextView.setChecked(check); 
//		
//		return converView;
//	}
	
	class ViewHolder{
		CheckedTextView myCheckedTextView;
	}
	
}
