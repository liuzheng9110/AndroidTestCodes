package com.example.androidtest.sliding;

import com.example.androidtest.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

/**
 * SlidingPaneLayoutRightFragment
 * @author liuzheng
 * @date 2014-10-27 ÏÂÎç3:52:17
 *
 */
public class SlidingPaneLayoutRightFragment extends Fragment {
	
	private Button leftBtn;
	private WebView webView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.sliding_panel_layout_right, container, false);
		
		leftBtn = (Button) v.findViewById(R.id.title_left_btn);
		leftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((SlidingPaneLayoutAct)getActivity()).toggleSlidingPanel();
			}
		});
		
		webView = (WebView) v.findViewById(R.id.sliding_pane_lay_content);
		return v;
	}
	
	public WebView getWebView() {
		return webView;
	}
}
