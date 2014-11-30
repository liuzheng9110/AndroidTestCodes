package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtest.main.BaseActivity;

public class AndroidJs2JavaAct extends BaseActivity {
	
	private WebView webView;
	private Button button;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_js2java_layout);
		
		webView = (WebView) findViewById(R.id.webview);
		button = (Button) findViewById(R.id.webview_btn);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				webView.loadUrl("http://www.liu-zheng.com/test.html");
			}
		});
		
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		webView.loadUrl("http://www.liu-zheng.com/test.html");
		
		webView.addJavascriptInterface(this, "js2java");
	}
	
	public void  btnClick() {
		showShortToast("aaaaaaaaaaaaaaaa click no args ");
	}
	public void  btnClick(String val) {
		System.out.println("val..." + val);
		showShortToast("aaaaaaaaaaaaaaaa click has args " + val);
	}
}
