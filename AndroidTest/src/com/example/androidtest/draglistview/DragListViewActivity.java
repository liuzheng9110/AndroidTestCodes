package com.example.androidtest.draglistview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.draglistview.DragSortGridView.OnReorderingListener;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: DragListViewActivity.java
 *  Function: 可拖动 listview 、 gridview 
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年4月22日 下午2:07:07
 *  @Copyright http://liuz.me 
 *  @url http://blog.csdn.net/vipzjyno1/article/details/25005851
 */
public class DragListViewActivity extends BaseActivity {
	
	private DragSortGridView dGridView;
	private ColorsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_listview_layout);
		
		dGridView = (DragSortGridView) findViewById(R.id.dragSortGridView);
		
		int[] colors = new int[10]; // 定义gridview item个数
		Random random = new Random();
		// 随机产生出不同颜色
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff));
		}
		
		adapter = new ColorsAdapter(this, colors);
		dGridView.setAdapter(adapter);
		// 移动重排序监听   
		dGridView.setOnReorderingListener(new OnReorderingListener() {
			@Override
			public void onReordering(int fromPosition, int toPosition) {
				Log.i("liuz", "fromPosition..." + fromPosition + "####" + "toPosition..." + toPosition);
				if (adapter != null) {
					adapter.reorder(fromPosition, toPosition);
				}else{
					
				}
			}
		});
	}
	
	
	class ColorsAdapter extends BaseAdapter{
		// 颜色和位置 集合
		private List<Integer> colors;
		private List<Integer> positions;
		private Context mContext;
		
		public ColorsAdapter(Context context, int[] colors) {
			this.colors = new ArrayList<Integer>();
			this.positions = new ArrayList<Integer>();
			this.mContext = context;
			
			// 
			for (int color : colors) {
				this.colors.add(color);
				this.positions.add(positions.size());
			}
		}
		
		/**
		 * 
		 *  Function: 重新排序
		 *  @author liuzheng
		 *  @created 2015年4月22日 下午2:51:27 
		 *  @param from 初始坐标
		 *  @param to   目的坐标
		 */
		public void reorder(int from, int to){
			if (from != to) {
				int color = colors.remove(from); // 获取选中移除的 item
				colors.add(to, color);	// 将移除的 item 添加到指定的位置 to 
				
				int position = positions.remove(from); // 同上
				positions.add(to, position);
				
				notifyDataSetChanged();
			}
		}
		
		@Override
		public int getCount() {
			return colors.size();
		}

		@Override
		public Object getItem(int position) {
			return colors.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 初始化显示控件
			TextView textView = (TextView) convertView;
			
			if (textView == null) {
				textView = new TextView(mContext);
				textView.setBackgroundColor(colors.get(position)); // 设置背景颜色 
				textView.setText(Integer.toString(positions.get(position))); // 设置文本
				textView.setGravity(Gravity.CENTER); // 设置文本居中
				textView.setPadding(5, 5, 5, 5); // 设置边距
				textView.setMinHeight(100); // 最小高度
				
			}else{
				textView.setBackgroundColor(colors.get(position));
				textView.setText(Integer.toString(positions.get(position)));
			}
			
			return textView;
		}
	}
}
