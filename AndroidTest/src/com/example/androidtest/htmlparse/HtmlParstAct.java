package com.example.androidtest.htmlparse;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.util.Constants;

/**
 * 
 *  Class Name: HtmlParstAct.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-11-25 ÉÏÎç11:16:46
 */
public class HtmlParstAct extends BaseActivity {
	
	private ListView htmlListView;
	private ArrayList<HtmlParseListBean> htmlParseListBeans = new ArrayList<HtmlParseListBean>();
	private ArrayList<HtmlParseContentBean> htmlParseContentBeans = new ArrayList<HtmlParseContentBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.html_parse_layout);
		
		htmlListView = (ListView) findViewById(R.id.html_listView);
		
//		requestKzhContentList();
		
		requestKzhContent();
		
	}
	
	private void requestKzhContent() {
		showProgressDialog();
		new Thread(){
			public void run() {
				KanZhiHuParseContent.getContentHtml();
				
				
			};
		}.start();
	}
	
	private void requestKzhContentList() {
		showProgressDialog();
		new Thread(){
			public void run() {
				
				htmlParseListBeans = KanZhiHuParseList.getListHtml();
				
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			dismissProgressDialog();
			if (msg.what == 0) {
				htmlListView.setAdapter(new HtmlParseAdapter(HtmlParstAct.this, htmlParseListBeans, Constants.KANZHIHU_LIST));
			}else {
				htmlListView.setAdapter(new HtmlParseAdapter(HtmlParstAct.this, htmlParseContentBeans, Constants.KANZHIHU_CONTENT));
			}
		};
	};
}
