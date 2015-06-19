package com.example.androidtest.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.androidtest.R;

@SuppressLint("ValidFragment")
public class FragmentDetail extends Fragment implements OnClickListener {
	private View view;
	private TextView textView;
	private LinearLayout button;
	
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
		
		button = (LinearLayout) view.findViewById(R.id.button);
		button.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			Toast.makeText(getActivity(), "...", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
