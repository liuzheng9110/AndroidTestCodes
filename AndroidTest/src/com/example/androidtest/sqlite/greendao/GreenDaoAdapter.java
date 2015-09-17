package com.example.androidtest.sqlite.greendao;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Notification.Action;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.sqlite.greendao.bean_dao.UserInfo;

public class GreenDaoAdapter extends BaseAdapter {

	private Activity mActivity;
	private LayoutInflater mInflater;
	private ArrayList<UserInfo> mSimpleDatas;
	
	public GreenDaoAdapter(Activity activity, ArrayList<UserInfo> simpleDatas) {
		this.mActivity = activity;
		this.mSimpleDatas = simpleDatas;
		mInflater = LayoutInflater.from(mActivity);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSimpleDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSimpleDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

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

		UserInfo simpleData = mSimpleDatas.get(position);
		holder.idTv.setText(simpleData.getId().toString());
		holder.nameTv.setText(simpleData.getUserName());
		holder.sexTv.setText(simpleData.getUserSex());
		holder.ageTv.setText(simpleData.getUserAge());

		return convertView;
	}

	class SimpleDataHolder {
		TextView idTv;
		TextView nameTv;
		TextView sexTv;
		TextView ageTv;
	}
}