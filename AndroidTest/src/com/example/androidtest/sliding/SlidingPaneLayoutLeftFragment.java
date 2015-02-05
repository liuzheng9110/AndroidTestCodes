package com.example.androidtest.sliding;

import com.example.androidtest.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * SlidingPaneLayoutLeftFragment 
 * @author liuzheng
 * @date 2014-10-27 下午3:52:07
 *
 */
public class SlidingPaneLayoutLeftFragment extends Fragment {
	
	private SlidingPaneLayoutAct slidingPaneLayoutAct;
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.sliding_panel_layout_left, container, false);
		initListView(v);
		
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		if (!(activity instanceof SlidingPaneLayoutAct)) {
			throw new ClassCastException();
		}
		slidingPaneLayoutAct = (SlidingPaneLayoutAct) activity;
		super.onAttach(activity);
	}
	
	private void initListView(View v) {
		// TODO Auto-generated method stub
		listView = (ListView) v.findViewById(R.id.sliding_pane_lay_listview);
		listView.setAdapter(new ArrayAdapter<String>(
				SlidingPaneLayoutLeftFragment.this.getActivity(),
				android.R.layout.simple_list_item_1, new String[] {"Google","百度","网易","腾讯","新浪","搜狐"}));		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				switch (position) {
				case 0:
					slidingPaneLayoutAct.onItemClick("http://www.gfsoso.com");
					break;
				case 1:
					slidingPaneLayoutAct.onItemClick("http://www.baidu.com");
					break;
				case 2:
					slidingPaneLayoutAct.onItemClick("http://www.163.com");
					break;
				case 3:
					slidingPaneLayoutAct.onItemClick("http://www.qq.com");
					break;
				case 4:
					slidingPaneLayoutAct.onItemClick("http://www.sina.com");
					break;
				case 5:
					slidingPaneLayoutAct.onItemClick("http://www.sohu.com");
					break;
				default:
					slidingPaneLayoutAct.onItemClick("http://wen.lu");
					break;
				}
			}
		});
	}
	
	public interface onLeftItemClick{
		public void onItemClick(String url);
	}
	
}
