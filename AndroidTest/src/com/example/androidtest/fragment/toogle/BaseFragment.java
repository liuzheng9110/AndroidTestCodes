package com.example.androidtest.fragment.toogle;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;


public class BaseFragment extends Fragment {
	
	private Toast mToast;
	
	public BaseFragment() {
	}
	
	protected void requestDataByNet() {}
	
	protected void setMoniData() {}

	public void click_listener(View v){}
	
	private void showToast(String text, int duration){
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, duration);
		}else{
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		mToast.show();
	}
	
	protected void showShortToast(String text) {
		showToast(text, Toast.LENGTH_SHORT);
	}
	
}

