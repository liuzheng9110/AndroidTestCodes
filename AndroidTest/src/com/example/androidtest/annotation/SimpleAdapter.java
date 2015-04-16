package com.example.androidtest.annotation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.FindView;

import com.example.androidtest.R;

public class SimpleAdapter extends BaseAdapter {

	private static final String[] CONTENTS = "The quick brown fox jumps over the lazy dog".split(" ");
	
	private LayoutInflater mInflater;
	public SimpleAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return CONTENTS.length;
	}

	@Override
	public Object getItem(int position) {
		return CONTENTS[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		}else {
			convertView = mInflater.inflate(R.layout.simple_default_sqlite_layout_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		
		// 获取 item 数据
		String word = (String) getItem(position);
		holder.idTv.setText(position+"");
		holder.nameTv.setText(word);
		holder.ageTv.setText(word.length()+"");
		holder.sexTv.setText("");
		
		return convertView;
	}
	
	static class ViewHolder{
		// 6.1.0
//		@InjectView(R.id.id_text) TextView idTv;
//		@InjectView(R.id.name_text) TextView nameTv;
//		@InjectView(R.id.sex_text) TextView sexTv;
//		@InjectView(R.id.age_text) TextView ageTv;
		
		//7.x
		@FindView(R.id.id_text) TextView idTv;
		@FindView(R.id.name_text) TextView nameTv;
		@FindView(R.id.sex_text) TextView sexTv;
		@FindView(R.id.age_text) TextView ageTv;
		
		public ViewHolder(View view) {
			// 6.1.0
//			ButterKnife.inject(this, view);
			// 7.x
			ButterKnife.bind(this, view);
		}
	}
}
