package com.example.androidtest.htmlparse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class KanZhiHuParse {
	
	private static ArrayList<HtmlParseBean> htmlParseBeans = new ArrayList<HtmlParseBean>();
	
	public static ArrayList<HtmlParseBean> getHtmlParseBeans() {
		return htmlParseBeans;
	}
	
	public static void getContentHtml() {
		try {
			Document document = Jsoup.parse(new java.net.URL(Constants.KANZHIHU_URL), 5000);
//			System.out.println(document.outerHtml());  // 整个html 文档
			
			Elements elements = document.select("article");
			System.out.println(elements.size()+"\n===============================================");
//			Element allElement = elements.get(0);// 总节点
//			System.out.println(element.outerHtml());// 某一个 article 的html 内容
			
			for (int i = 0; i < elements.size(); i++) {
				Element allElement = elements.get(i);
				articleHtmlParse(allElement);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 *  Function:文章内容解析
	 *  @author liuzheng
	 *  @created 2014-12-2 下午2:58:58 
	 *  @param allElement 
	 */
	private static void articleHtmlParse(Element allElement) {
		HtmlParseBean htmlParseBean = new HtmlParseBean();
		
		Elements elements = null;
		//////////////////////image link//////////////////////////////////
		elements = allElement.select("div[class=post-thumbnail]"); // 链接 图片 内容
		
		// get html id
		String articalCSSIdVal = allElement.attr("id");// 获取 article id 属性值
		int length = articalCSSIdVal.length();
		String articalCSSIdValId = articalCSSIdVal.substring(length-4, length);
//		System.out.println(articalCSSIdVal + "...get attr val..." + articalCSSIdValId);
		
		htmlParseBean.setHtmlId(articalCSSIdValId);
		
		Element linkImageElement = elements.get(0);// 某一个 div[class=post-thumbnail] 的html 内容
		elements = linkImageElement.getElementsByTag("a");// a 链接
		linkImageElement = elements.get(0);// 获取 a 标签的值
//		System.out.println(linkImageElement.attr("title") +"_________"+ linkImageElement.attr("href"));// 获取 a 标签的title and href 值
		
		htmlParseBean.setHtmlImageTitle(linkImageElement.attr("title"));
		htmlParseBean.setHtmlUrl(linkImageElement.attr("href"));
		
		elements = linkImageElement.getElementsByTag("img");// a 链接
		linkImageElement = elements.get(0);// 获取 image 标签的值
//		System.out.println(linkImageElement.attr("width") +"_________"+ linkImageElement.attr("height") +"_________"+ linkImageElement.attr("src"));// 获取 a 标签的title and href 值
		
		htmlParseBean.setHtmlImageUrl(linkImageElement.attr("src"));
		
		///////////////////////////post date catagory/////////////////////////////
		elements = allElement.select("div[class=post-meta group]"); // 发布时间   内容分类 
		Element postGroupElement = elements.get(0);
		//System.out.println(postGroupElement.outerHtml());// 
		
		elements = postGroupElement.select("p[class=post-category]"); // 内容分类
		Element groupElement = elements.get(0);// 分类链接节点
		Elements cataElements = groupElement.getElementsByTag("a"); // 分类链接节点 中的 a 标签
		Elements dateElements = postGroupElement.select("p[class=post-date]"); // 发布时间
//		System.out.println(cataElements.get(0).text() +"_________"+ cataElements.get(0).attr("href") +"_______" + dateElements.text());// 内容分类 链接 发布时间
		
		htmlParseBean.setHtmlCataName(cataElements.get(0).text());
		htmlParseBean.setHtmlCataUrl(cataElements.get(0).attr("href"));
		
		htmlParseBean.setHtmlType(cataElements.get(0).text());
		htmlParseBean.setHtmlTime(dateElements.text());
		
		/////////////////////////post title///////////////////////////////
		elements = allElement.select("h2[class=post-title]");// 标题和详细内容链接
		Element postTitleElement = elements.get(0);
//		System.out.println(postTitleElement.text() +"_________"+ postTitleElement.getElementsByTag("a").attr("href"));
		
		htmlParseBean.setHtmlTitle(postTitleElement.text());
		htmlParseBean.setHtmlUrl(postTitleElement.getElementsByTag("a").attr("href"));
		
		////////////////////////////////////////////////////////
		elements = allElement.select("div[class=entry excerpt]"); // 内容简单描述
		Element descElement = elements.get(0); // 
//		System.out.println("_________"+ descElement.getElementsByTag("p").text());
		
		htmlParseBean.setHtmlDesc(descElement.getElementsByTag("p").text());
		
		Log.i("liuz", htmlParseBean.toString());
		
		// 
		htmlParseBeans.add(htmlParseBean);
		////////////////////////////////////////////////////////
	}
}
