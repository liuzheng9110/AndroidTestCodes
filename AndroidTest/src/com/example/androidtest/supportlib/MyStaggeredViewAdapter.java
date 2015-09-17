package com.example.androidtest.supportlib;

import java.util.ArrayList;
import java.util.List;

import com.example.androidtest.R;

import android.content.Context;
import android.net.IpPrefix;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

public class MyStaggeredViewAdapter extends Adapter<MyRecyclerViewHolder> {

	private Context mContext;
	private LayoutInflater mInflater;
	public List<String> datas;
	public List<Integer> heights;
	
	public MyStaggeredViewAdapter(Context context){
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

		datas = new ArrayList<String>();
		heights = new ArrayList<Integer>();
		
		for (int i = 'A'; i < 'z'; i++) {
			datas.add((char)i + "");
		}
		
		// 随机瀑布流 item 高度
		for (int i = 0; i < datas.size(); i++) {
			heights.add((int) (Math.random() * 300) + 200);
		}
	}
	
	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(MyRecyclerViewHolder holder, final int position) {
		if (onItemClickListener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onItemClickListener.onItemClick(v, position);
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					onItemClickListener.onItemLongClick(v, position);
					return true;
				}
			});
			
			// 设置瀑布流 item 高度
			ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
			lp.height = heights.get(position);
			holder.textView.setLayoutParams(lp);
			
			holder.textView.setText(datas.get(position));
		}
	}

	@Override
	public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = mInflater.inflate(R.layout.support_lib_item, parent, false);
		MyRecyclerViewHolder holder = new MyRecyclerViewHolder(view);
		return holder;
	}
	
	//// 
	public interface OnItemClickListener {
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}
	
	public OnItemClickListener onItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	
}
