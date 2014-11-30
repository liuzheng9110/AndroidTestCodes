package com.example.androidtest.htmlparse;

import java.io.Serializable;

/**
 * 
 * Class Name: HtmlParseBean.java Function:
 * 
 * @author liuzheng
 * @version 1.0
 * @created 2014-11-25 上午11:28:11
 * @Copyright 方欣科技湖南分公司
 */
public class HtmlParseBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String htmlId;   // 序号
	private String htmlTitle;// 标题
	private String htmlDesc; // 内容简述
	private String htmlType; // 标签类型 (近日热门、历史精华、昨日最新)
	private String htmlTime; // 发布时间

	
	public String getHtmlId() {
		return htmlId;
	}
	
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}
	
	public String getHtmlTitle() {
		return htmlTitle;
	}

	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}

	public String getHtmlDesc() {
		return htmlDesc;
	}
	
	public void setHtmlDesc(String htmlDesc) {
		this.htmlDesc = htmlDesc;
	}
	
	public String getHtmlType() {
		return htmlType;
	}

	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}

	public String getHtmlTime() {
		return htmlTime;
	}

	public void setHtmlTime(String htmlTime) {
		this.htmlTime = htmlTime;
	}

}
