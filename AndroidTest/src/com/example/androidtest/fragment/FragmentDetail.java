package com.example.androidtest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtest.R;

public class FragmentDetail extends Fragment {
	private View view;
	private TextView textView;
	
	private int mPosition = -1;
	
	public FragmentDetail(int position) {
		// TODO Auto-generated constructor stub
		this.mPosition = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_left, container, false);
		
		textView = (TextView) view.findViewById(R.id.textView_left);
		textView.setText("You Click " + mPosition);
		
		return view;
	}
}
