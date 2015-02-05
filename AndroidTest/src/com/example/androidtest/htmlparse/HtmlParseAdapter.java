package com.example.androidtest.htmlparse;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.util.Constants;

public class HtmlParseAdapter extends BaseAdapter {
	
	private Context mContext;
	private ArrayList<?> mHtmlParseBeans;
	private LayoutInflater mInflater;
	private int mType = 0;
	
	public HtmlParseAdapter(Context context, ArrayList<?> htmlParseBeans, int type) {
		this.mContext = context;
		this.mHtmlParseBeans = htmlParseBeans;
		this.mInflater = LayoutInflater.from(mContext);
		this.mType = type;
		
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
		if (mType == Constants.KANZHIHU_LIST) {
			getContentListView(convertView, position);
		}else if (mType == Constants.KANZHIHU_CONTENT) {
			getContentView(convertView, position);
		}
		return convertView;
	}

	//////////////////////////////////////////////////
	void getContentView(View convertView, int position) {
		HtmlParseContentHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.html_parse_list_item, null);
			holder = new HtmlParseContentHolder();
			
			
			
			convertView.setTag(holder);
		}else {
			holder = (HtmlParseContentHolder) convertView.getTag();
		}
	}
	
	class HtmlParseContentHolder{
		TextView htmlTitle;
		ImageView htmlAvatar;
		TextView htmlSummary;
		TextView htmlAgreeText;
		TextView htmlDesc;
	}
	
	//////////////////////////////////////////////////
	void getContentListView(View convertView, int position){
		HtmlParseListHolder holder;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.html_parse_list_item, null);
			holder = new HtmlParseListHolder();
			
			holder.htmlTitle = (TextView) convertView.findViewById(R.id.html_title);
			holder.htmlDesc = (TextView) convertView.findViewById(R.id.html_desc);
			holder.htmlTime = (TextView) convertView.findViewById(R.id.html_time);
			holder.htmlType = (TextView) convertView.findViewById(R.id.html_type);
			
			convertView.setTag(holder);
		}else {
			holder = (HtmlParseListHolder) convertView.getTag();
		}
		
		// 设置内容
		HtmlParseListBean htmlParseBean = (HtmlParseListBean) mHtmlParseBeans.get(position);
		holder.htmlTitle.setText(htmlParseBean.getHtmlTitle());
		holder.htmlDesc.setText(htmlParseBean.getHtmlDesc());
		holder.htmlTime.setText(htmlParseBean.getHtmlTime());
		holder.htmlType.setText(htmlParseBean.getHtmlType());

	}
	
	class HtmlParseListHolder {
		TextView htmlTitle;// 标题
		TextView htmlDesc; // 描述
		TextView htmlTime; // 时间
		TextView htmlType; // 类型
	}
	
}
