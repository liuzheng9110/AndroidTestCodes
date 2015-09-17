package com.example.androidtest.supportlib;

import com.example.androidtest.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewHolder extends ViewHolder {

	public TextView textView;
	
	public MyRecyclerViewHolder(View itemView) {
		super(itemView);
		textView = (TextView) itemView.findViewById(R.id.support_lib_item_textview);
	}

}
