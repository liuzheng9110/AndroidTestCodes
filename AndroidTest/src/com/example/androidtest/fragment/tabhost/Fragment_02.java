package com.example.androidtest.fragment.tabhost;

import com.example.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment_02 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_fragment, container, false);

		ImageView imageView = (ImageView) v.findViewById(R.id.imageView1);
		imageView.setImageResource(R.drawable.ic_launcher_02);
		return v;
	}
}
