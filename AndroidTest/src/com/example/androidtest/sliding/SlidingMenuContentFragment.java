package com.example.androidtest.sliding;

import com.example.androidtest.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class SlidingMenuContentFragment extends Fragment {
	
	private Button openMenuBtn;
	
	private String mText;
	private int mType;
	
	public SlidingMenuContentFragment(){};
	
	public SlidingMenuContentFragment(String text, String btnText, int type) {
		// TODO Auto-generated constructor stub
		this.mText = text;
		this.mType = type;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initView(inflater, container);
	}

	private View initView(LayoutInflater inflater, ViewGroup container){
		View view = null;
		switch (mType) {
		case 1:
			view = inflater.inflate(R.layout.sliding_menu_content_fragment, container, false);
			
			openMenuBtn = (Button) view.findViewById(R.id.open_menu_btn);
			openMenuBtn.setText("Open Menu By " + mText);
			openMenuBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((SlidingMenuAct) getActivity()).switchMenu();
				}
			});
			return view;
		case 2:
			view = inflater.inflate(R.layout.sliding_drawer_fragment, container, false);
			
			return view;
		default:
			view = inflater.inflate(R.layout.sliding_menu_content_fragment, container, false);
			return view;
		}
	}
	
	protected void openMenuByType() {
		switch (mType) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}
	}
}
