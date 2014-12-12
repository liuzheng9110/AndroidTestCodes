package com.example.androidtest.util;

import java.util.Map;

public class RequestVo {

	public String url;
	public Map<String, String> dataMap;

	public RequestVo() {
	}

	public RequestVo(String url, Map<String, String> dataMap) {
		this.url = url;
		this.dataMap = dataMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
}
