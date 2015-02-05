package com.example.androidtest.a_foresee.gridview;

import java.util.ArrayList;

import com.example.androidtest.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * 
 *  Class Name: A_ForeseeAdapter.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015-1-8 ÉÏÎç11:27:52
 *  @Copyright http://liuz.me
 */
public class A_ForeseeAdapter extends BaseAdapter {

	private Activity mActivity;
	private ArrayList<?> mDataList;
	private int mType = 0;
	private LayoutInflater mInflater;
	
	public A_ForeseeAdapter(Activity activity, ArrayList<?> dataList, int type){
		this.mActivity = activity;
		this.mDataList = dataList;
		this.mType = type;
		
		mInflater = LayoutInflater.from(mActivity);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (mType == 1) {
			convertView = getViewGridView(convertView, position);
		}else {
			
		}
		return convertView;
	}
	
	/***/
	private View getViewGridView(View convertView, int position) {
		final GridViewHolder holder;
		
		if (convertView == null) {
			holder = new GridViewHolder();
			convertView = mInflater.inflate(R.layout.taxper_grid_layout_item, null);
			
			holder.textView = (TextView) convertView.findViewById(R.id.taxper_gridview_item_textview);
			
			convertView.setTag(holder);
		}else {
			holder = (GridViewHolder) convertView.getTag();
		}
		
		
		
		return convertView;
	}
	
	class GridViewHolder{
		TextView textView;
	}
	
	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	

}
