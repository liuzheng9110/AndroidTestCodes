package com.example.androidtest.htmlparse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.androidtest.util.Constants;

/**
 * 
 *  Class Name: KanZhiHuParseContent.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-15 下午10:07:55
 *  @Copyright http://liuz.me
 */
public class KanZhiHuParseContent {
	
	private static ArrayList<HtmlParseContentBean> htmlParseContentBeans = new ArrayList<HtmlParseContentBean>();

	public static void main(String[] args) {
		getContentHtml();
	}
	
	public static ArrayList<HtmlParseContentBean> getContentHtml() {
		try {
			Document document = Jsoup.parse(new java.net.URL(Constants.KANZHIHU_CONTENT_URL), 5000);
//			System.out.println(document.outerHtml());  // 整个html 文档
			
			Elements elements = document.select("div.entry-inner > ul > li");
			System.out.println(elements.size()+"\n===============================================");
//			Element allElement = elements.get(0);// 总节点
//			System.out.println(element.outerHtml());// 某一个 article 的html 内容
			
//			for (int i = 0; i < elements.size(); i++) {
//				Element contentListElement = elements.get(i);
//				entryInnerUlHtmlParse(contentListElement);
//			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return htmlParseContentBeans;
	}

	private static void entryInnerUlHtmlParse(Element allElement) {
		// TODO Auto-generated method stub
		
	}
	
}
