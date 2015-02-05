package com.example.androidtest;

import java.util.ArrayList;

import android.content.Context;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class InputFaceListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<String> mdataList;
	
	public InputFaceListAdapter(Context context, ArrayList<String> dataList){
		this.mContext = context;
		this.mdataList = dataList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mdataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mdataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 
	 * @param item
	 */
	public void addItem(String item){
		mdataList.add(item);
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView;
		if (convertView==null) {
			textView = new TextView(mContext);
			// 设置宽高
			textView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// 垂直居中显示
			textView.setGravity(Gravity.CENTER_VERTICAL);
		}else {
			textView = (TextView) convertView;
		}
		// 获取发送字符串
		String itemStr = mdataList.get(position);
		
		// 正则表达式，用来判断消息内是否有表情
		String zhengze = "f_static_[0-9]{3}|f0[0-9]{2}|f1[0-9]{2}|\\[\\w+\\]";
		try {
			SpannableString spannableString = InputFaceUtil.getExpressionString(mContext, itemStr, zhengze);
			textView.setText(spannableString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return textView;
	}

}
