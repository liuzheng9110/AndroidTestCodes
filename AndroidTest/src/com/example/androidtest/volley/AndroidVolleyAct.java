package com.example.androidtest.volley;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.androidtest.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidVolleyAct extends Activity implements OnClickListener {
	
	private String REQ_URL_STRING = "http://api.map.baidu.com/telematics/v3/weather?location=%E9%95%BF%E6%B2%99&output=json&ak=640f3985a6437dad8135dae98d775a09";
	private String REQ_URL_JSON = "http://api.map.baidu.com/telematics/v3/weather?location=%E9%95%BF%E6%B2%99&output=json&ak=640f3985a6437dad8135dae98d775a09";
	private String REQ_URL_IMAGE = "http://avatar.csdn.net/6/6/D/1_lfdfhl.jpg";
	private String REQ_URL_NET_IMAGE = "http://image227-c.poco.cn/mypoco/myphoto/20140716/20/6524982620140716202956046_640.jpg";
	
	private String REQ_URL_WEBSERVICE = "http://www.baidu.com";
	
	private Button buttonString, buttonJson, buttonImage, buttonNetImage;
	private TextView resultTextView;
	private ImageView imageView;
	private NetworkImageView networkImageView;
	
	private RequestQueue requestQueue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volley_lay);
		
		buttonString = (Button) findViewById(R.id.button_string);
		buttonJson = (Button) findViewById(R.id.button_json);
		buttonImage = (Button) findViewById(R.id.button_image);
		buttonNetImage = (Button) findViewById(R.id.button_net_image);
		imageView = (ImageView) findViewById(R.id.image_view);
		networkImageView = (NetworkImageView) findViewById(R.id.net_imageview);
		
		buttonString.setOnClickListener(this);
		buttonJson.setOnClickListener(this);
		buttonImage.setOnClickListener(this);
		buttonNetImage.setOnClickListener(this);
		
		resultTextView = (TextView) findViewById(R.id.result);
		
		// 创建请求队列
		requestQueue = Volley.newRequestQueue(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_string:
			StringRequest stringRequest = new StringRequest(REQ_URL_STRING, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					Log.i("liuz", response);
					resultTextView.setText(response);
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
					Log.i("liuz", volleyError.toString());
				}
			});
			
			stringRequest.setShouldCache(true);
			requestQueue.add(stringRequest);
			break;
		case R.id.button_json:
			JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.POST, REQ_URL_JSON, null, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject jsonObject) {
					Log.i("liuz", jsonObject.toString());
					resultTextView.setText(jsonObject.toString());
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
					Log.i("liuz", volleyError.toString());
				}
			});
			requestQueue.add(jsonRequest);
			break;
		case R.id.button_image:
			final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
			ImageCache imageCache = new ImageCache() {
				
				@Override
				public void putBitmap(String arg0, Bitmap arg1) {
					// TODO Auto-generated method stub
					lruCache.put(arg0, arg1);
				}
				
				@Override
				public Bitmap getBitmap(String arg0) {
					// TODO Auto-generated method stub
					return lruCache.get(arg0);
				}
			};
			ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
			ImageListener imageListener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher_01);
			imageLoader.get(REQ_URL_IMAGE, imageListener);
			
			break;
		case R.id.button_net_image:
			final LruCache<String, Bitmap> lruCache1 = new LruCache<String, Bitmap>(20);
			ImageCache imageCache1 = new ImageCache() {
				
				@Override
				public void putBitmap(String arg0, Bitmap arg1) {
					// TODO Auto-generated method stub
					lruCache1.put(arg0, arg1);
				}
				
				@Override
				public Bitmap getBitmap(String arg0) {
					// TODO Auto-generated method stub
					return lruCache1.get(arg0);
				}
			};
			ImageLoader netImageLoader = new ImageLoader(requestQueue, imageCache1);
			networkImageView.setTag("url");
			networkImageView.setImageUrl(REQ_URL_NET_IMAGE, netImageLoader);
			break;
		default:
			break;
		}
	}
}
