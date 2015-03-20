package com.example.androidtest.fragment.tabhost;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.androidtest.R;

public class Fragment_01 extends Fragment {
	
	private int resId = R.drawable.icon_nav_find_s;
	private int titleId = R.string.chat;
	
	private FragmentActivity activity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getArguments();
		int key = bundle.getInt("key");
		switch (key) {
		case 0:
			resId = R.drawable.icon_nav_reply_s;
			titleId = R.string.chat;
			break;
		case 1:
			resId = R.drawable.icon_nav_new_s;
			titleId = R.string.dynamic;
			break;
		case 2:
			resId = R.drawable.icon_nav_find_s;
			titleId = R.string.find;
			break;
		case 3:
			resId = R.drawable.icon_nav_me_s;
			titleId = R.string.mine;
			break;
		default:
			resId = R.drawable.icon_nav_add;
			titleId = R.string.chat;
			break;
		}
		activity = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		activity.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, resId);
		// set title
		activity.setTitle(titleId);
		
		View v = inflater.inflate(R.layout.tab_fragment, container, false);

		ImageView imageView = (ImageView) v.findViewById(R.id.imageView1);
		imageView.setImageResource(resId);
		
		return v;
	}
}
