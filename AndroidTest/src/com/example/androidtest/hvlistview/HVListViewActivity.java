package com.example.androidtest.hvlistview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: HVListViewActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015��8��31�� ����11:05:53
 *  @Copyright http://liuz.me 
 *  @url
 */
public class HVListViewActivity extends BaseActivity {
	
	private LayoutInflater mInflater;
	private HVListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hv_listview_layout);
		
		mListView = (HVListView) findViewById(R.id.hv_listView);
		mListView.mListHead = (LinearLayout) findViewById(R.id.head);
		mListView.setAdapter(new DataAdapter());

		mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	}
	
	private class DataAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 30;//�̶���ʾ50������
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.hv_listview_item, null);
			}

			((TextView) convertView.findViewById(R.id.item1)).setText("������ͷ"+position);
			for (int i = 0; i < 8; i++) {
				((TextView) convertView.findViewById(R.id.item2 + i)).setText("����" + position + "��" + (i + 2) + "��");
			}

			//У��������ͬʱ���º����ҹ������ִ�λ�����
			View child = ((ViewGroup) convertView).getChildAt(1);
			int head = mListView.getHeadScrollX();
			if (child.getScrollX() != head) {
				child.scrollTo(mListView.getHeadScrollX(), 0);
			}
			return convertView;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}
	
}	
