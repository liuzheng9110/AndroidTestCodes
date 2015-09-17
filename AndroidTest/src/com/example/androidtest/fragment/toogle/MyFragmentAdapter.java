package com.example.androidtest.fragment.toogle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.sqlite.SimpleData;
import com.example.androidtest.sqlite.greendao.bean_dao.UserInfo;

public class MyFragmentAdapter<T> extends BaseAdapter {

	private Activity mActivity;
	private LayoutInflater mInflater;
	private List<T> mDataList = new ArrayList<T>();

	public MyFragmentAdapter(Activity activity, List<T> dataList) {
		this.mActivity = activity;
		this.mDataList = dataList;
		mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SimpleDataHolder holder;
		if (convertView == null) {
			holder = new SimpleDataHolder();
			convertView = mInflater.inflate(R.layout.simple_sqlite_layout_item, null);

			holder.idTv = (TextView) convertView.findViewById(R.id.id_text);
			holder.nameTv = (TextView) convertView.findViewById(R.id.name_text);
			holder.sexTv = (TextView) convertView.findViewById(R.id.sex_text);
			holder.ageTv = (TextView) convertView.findViewById(R.id.age_text);

			convertView.setTag(holder);
		} else {
			holder = (SimpleDataHolder) convertView.getTag();
		}

		SimpleData simpleData = (SimpleData) mDataList.get(position);
		holder.idTv.setText(simpleData.getId());
		holder.nameTv.setText(simpleData.getName());
		holder.sexTv.setText(simpleData.getSex());
		holder.ageTv.setText(simpleData.getAge());

		return convertView;
	}

	class SimpleDataHolder {
		TextView idTv;
		TextView nameTv;
		TextView sexTv;
		TextView ageTv;
	}

}
