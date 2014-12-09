package com.example.androidtest.htmlparse;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: HtmlParstAct.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-11-25 上午11:16:46
 */
public class HtmlParstAct extends BaseActivity {
	
	private ListView htmlListView;
	private ArrayList<HtmlParseBean> htmlParseBeans = new ArrayList<HtmlParseBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.html_parse_layout);
		
		htmlListView = (ListView) findViewById(R.id.html_listView);
		
//		setData();
//
//		for (int i = 0; i < titles.length; i++) {
//			HtmlParseBean htmlParseBean = new HtmlParseBean();
//			htmlParseBean.setHtmlId(""+(i+1));
//			htmlParseBean.setHtmlTitle(titles[i]);
//			htmlParseBean.setHtmlDesc(descs[0]);
//			htmlParseBean.setHtmlType(types[i]);
//			htmlParseBean.setHtmlTime(times[i]);
//			
//			htmlParseBeans.add(htmlParseBean);
//		}
//		htmlListView.setAdapter(new HtmlParseAdapter(this, htmlParseBeans));
		///////////////////////////////////////////////////////////////////
		
		showProgressDialog();
		
		new Thread(){
			public void run() {
				
				KanZhiHuParse.getContentHtml();
				htmlParseBeans = KanZhiHuParse.getHtmlParseBeans();
				
				handler.sendEmptyMessage(0);
			};
		}.start();
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			htmlListView.setAdapter(new HtmlParseAdapter(HtmlParstAct.this, htmlParseBeans));
			dismissProgressDialog();
		};
	};
	
	
	private String[] titles, descs, types, times;
	private void setData() {
		titles = new String[]{"2014年11月25日 昨日最新", "2014年11月25日 近日热门", "2014年11月25日 历史精华", "2014年11月24日 昨日最新", "2014年11月24日 近日热门", "2014年11月24日 历史精华"};
		descs = new String[]{"摘录了 [有哪个瞬间会让你想‘就一直这样下去多好’？]、[看见说的话阿卡家都是废话啊多少空间发挥哈的发挥的说法]、[adjfhajkhfak空间撒点回复可见萨芬]、[阿道夫京哈即可合法分开就阿訇多发d]、[阿的说法和房间爱还是对方阿卡家电话发放]"};
		types = new String[]{"昨日最新", "近日热门", "历史精华", "昨日最新", "近日热门", "历史精华"};
		times = new String[]{"2014-11-25 11:46:33", "2014-11-25 11:46:33", "2014-11-25 11:46:33", "2014-11-24 11:46:33", "2014-11-24 11:46:33", "2014-11-24 11:46:33"};
	}
}
