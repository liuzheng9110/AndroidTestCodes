package com.example.androidtest.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Utils {
	private static Toast mToast;
	
	/**
	 * 
	 * @param mContext
	 * @param text
	 */
	public static void showShortToast(Context mContext, String text){
		if (mToast==null) {
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		}else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 
	 * Function:设置listview高度 (注意 必须在 setadapter 之后)
	 * 
	 * @author liuzheng
	 * @created 2014-5-16 下午10:34:13
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}
