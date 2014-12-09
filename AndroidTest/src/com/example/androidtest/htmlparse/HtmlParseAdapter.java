package com.example.androidtest.htmlparse;

import java.util.ArrayList;

import com.example.androidtest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HtmlParseAdapter extends BaseAdapter {
	
	private Context mContext;
	private ArrayList<HtmlParseBean> mHtmlParseBeans;
	private LayoutInflater mInflater;
	
	public HtmlParseAdapter(Context context, ArrayList<HtmlParseBean> htmlParseBeans) {
		this.mContext = context;
		this.mHtmlParseBeans = htmlParseBeans;
		this.mInflater = LayoutInflater.from(mContext);
		
	}

	@Override
	public int getCount() {
		return mHtmlParseBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return mHtmlParseBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HtmlParstHolder holder;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.html_parse_list_item, null);
			holder = new HtmlParstHolder();
			
			holder.htmlTitle = (TextView) convertView.findViewById(R.id.html_title);
			holder.htmlDesc = (TextView) convertView.findViewById(R.id.html_desc);
			holder.htmlTime = (TextView) convertView.findViewById(R.id.html_time);
			holder.htmlType = (TextView) convertView.findViewById(R.id.html_type);
			
			convertView.setTag(holder);
		}else {
			holder = (HtmlParstHolder) convertView.getTag();
		}
		
		// 设置内容
		HtmlParseBean htmlParseBean = mHtmlParseBeans.get(position);
		holder.htmlTitle.setText(htmlParseBean.getHtmlTitle());
		holder.htmlDesc.setText(htmlParseBean.getHtmlDesc());
		holder.htmlTime.setText(htmlParseBean.getHtmlTime());
		holder.htmlType.setText(htmlParseBean.getHtmlType());
		
		return convertView;
	}

	class HtmlParstHolder {
		private TextView htmlTitle;// 标题
		private TextView htmlDesc; // 描述
		private TextView htmlTime; // 时间
		private TextView htmlType; // 类型
	}
	
}
