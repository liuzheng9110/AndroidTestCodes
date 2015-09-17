package com.example.androidtest.supportlib;

import java.util.ArrayList;
import java.util.List;

import com.example.androidtest.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

/**
 * 
 *  Class Name: MyRecyclerViewAdapter.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月20日 上午10:03:00
 *  @Copyright http://liuz.me 
 *  @url
 */
public class MyRecyclerViewAdapter extends Adapter<MyRecyclerViewHolder> {

	private Context mContext;
	public List<String> datas;
	private LayoutInflater mInflater;
	
	public MyRecyclerViewAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		datas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++) {
			datas.add((char)i + "");
		}
	}
	
	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
		if (onItemClickListener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onItemClickListener.onItemClick(holder.itemView, position);
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					onItemClickListener.onItemLongClick(holder.itemView, position);
					return true;
				}
			});
		}
		// 
		holder.textView.setText(datas.get(position));
	}

	@Override
	public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = mInflater.inflate(R.layout.support_lib_item, parent, false);
		MyRecyclerViewHolder holder = new MyRecyclerViewHolder(view);
		return holder;
	}
	
	public interface OnItemClickListener{
		void onItemClick(View view, int position);
		
		void onItemLongClick(View view, int position);
	}
	
	public OnItemClickListener onItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

}
