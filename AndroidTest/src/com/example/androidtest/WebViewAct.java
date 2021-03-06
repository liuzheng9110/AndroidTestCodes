package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidtest.util.Utils;

public class WebViewAct extends Activity {
	
	private ListView listView;
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_layout);
		
		webView = (WebView) findViewById(R.id.webview);
		webView.setInitialScale(0);

	    webView.setWebViewClient(new WebViewClient(){       
            public boolean shouldOverrideUrlLoading(WebView view, String url) {       
                view.loadUrl(url);       
                return true;       
            }       
	    });   
//	    webView.loadUrl("http://www.baidu.com");
	    webView.loadUrl("http://weibo.com");
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "aaaa",
						"bbb", "ccc", "ddd" }));
		Utils.setListViewHeightBasedOnChildren(listView);
		
	}
}
