<<<<<<< HEAD
package com.example.androidtest.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 *  Class Name: JsonParse.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-10 上午10:25:59
 *  @Copyright http://liuz.me
 */
public class JsonParse {
		
	/**
	 * 
	 *  Function:parse image urls
	 *  @author liuzheng
	 *  @created 2014-12-10 上午10:39:33 
	 *  @param jsonObject
	 *  @param urls
	 *  @return
	 *  @throws JSONException
	 */
	public static List<String> getImageUrls(JSONObject jsonObject, List<String> urls) throws JSONException {
		
		JSONObject responseObject = jsonObject.getJSONObject("tiripPackage");
		responseObject = responseObject.getJSONObject("businessContent");
		responseObject = responseObject.getJSONObject("response");
		
		JSONArray jsonArray = responseObject.getJSONArray("items");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject itemObject = jsonArray.getJSONObject(i);
			urls.add(itemObject.getString("fileUrl"));
		}
		return urls;
	}
}
=======
package com.example.androidtest.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 *  Class Name: JsonParse.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-10 上午10:25:59
 *  @Copyright http://liuz.me
 */
public class JsonParse {
		
	/**
	 * 
	 *  Function:parse image urls
	 *  @author liuzheng
	 *  @created 2014-12-10 上午10:39:33 
	 *  @param jsonObject
	 *  @param urls
	 *  @return
	 *  @throws JSONException
	 */
	public static List<String> getImageUrls(JSONObject jsonObject, List<String> urls) throws JSONException {
		
		JSONObject responseObject = jsonObject.getJSONObject("tiripPackage");
		responseObject = responseObject.getJSONObject("businessContent");
		responseObject = responseObject.getJSONObject("response");
		
		JSONArray jsonArray = responseObject.getJSONArray("items");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject itemObject = jsonArray.getJSONObject(i);
			urls.add(itemObject.getString("fileUrl"));
		}
		return urls;
	}
}
>>>>>>> e5cd185b03948bca73f0c3620744fe14e64ef5a1
