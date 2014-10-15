package com.example.androidtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

public class InputFaceAdapter extends BaseAdapter {

	private Context context;
	private Activity activity;
	private int[] faceIds;
	private String[] faceIdStr;
	private LayoutInflater mInflater;

	public InputFaceAdapter(Activity activity, int[] faceIds, String[] faceIdStr) {
		this.activity = activity;
		this.faceIds = faceIds;
		this.faceIdStr = faceIdStr;
		mInflater = LayoutInflater.from(activity);
	}

	@Override
	public int getCount() {
		return faceIds.length;
	}

	@Override
	public Integer getItem(int position) {
		return faceIds[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

//	public View getView(int position, View convertView, ViewGroup parent) {
//		final FaceHolder holder;
//		if (convertView==null) {
//			convertView = mInflater.inflate(R.layout.face_gridview_item, null);
//			holder = new FaceHolder();
//			holder.faceImage = (ImageView) convertView.findViewById(R.id.face_image_view);
//			
//			convertView.setTag(faceIdStr[position]);
//		}else {
//			holder = (FaceHolder) convertView.getTag();
//		}
//		
////		holder.faceImage.setTag(faceIdStr[position]);// 绑定参数 用于显示在edittext中
//		
//		holder.faceImage.setImageResource(faceIds[position]);
//		
//		return convertView;
//	};
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.face_gridview_item, null);
			imageView = (ImageView) convertView.findViewById(R.id.face_image_view);
			
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setTag(faceIdStr[position]);// 绑定参数 用于显示在edittext中

		imageView.setImageResource(faceIds[position]);
		return imageView;
	}
	
//	public View getView(int position, View convertView, ViewGroup parent){
//		ImageView imageView;
//		if (convertView == null)
//		{
//			imageView = new ImageView(activity);
//			// 设置图片n×n显示
//			imageView.setLayoutParams(new GridView.LayoutParams(56, 56));
//			// 设置显示比例类型
//			imageView.setScaleType(ImageView.ScaleType.CENTER);
//		}
//		else
//		{
//			imageView = (ImageView) convertView;
//		}
//		
//		imageView.setImageResource(faceIds[position]);
//		if(position < 65)
//			imageView.setTag("["+position+"]");
//		else if(position < 100)
//			imageView.setTag("["+(position+1)+"]");
//		else
//			imageView.setTag("["+(position+2)+"]");
//		
//		return imageView;
//	}
	
	class FaceHolder{
		ImageView faceImage;
	}
	
}
