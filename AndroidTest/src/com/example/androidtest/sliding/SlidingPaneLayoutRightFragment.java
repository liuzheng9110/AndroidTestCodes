package com.example.androidtest.sliding;

import com.example.androidtest.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * SlidingPaneLayoutRightFragment
 * @author liuzheng
 * @date 2014-10-27 ÏÂÎç3:52:17
 *
 */
public class SlidingPaneLayoutRightFragment extends Fragment {
	private WebView webView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.sliding_panel_layout_right, container, false);
		webView = (WebView) v.findViewById(R.id.sliding_pane_lay_content);
		return v;
	}
	
	public WebView getWebView() {
		return webView;
	}
}
