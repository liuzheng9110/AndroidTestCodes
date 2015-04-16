package com.example.androidtest.http;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class HttpMainAct extends Activity {
	
	private ListView listView;
	private TextView textView;
	private ArrayList dataList = new ArrayList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_main_act);
		
		textView = (TextView) findViewById(R.id.webservice_text);
	}

	public void clickListener(View v) {
		switch (v.getId()) {
		case R.id.http_post:
	        new MyThread().start();
			break;
		}
	}
	
	

	class MyThread extends Thread{
		@Override
		public void run() {
			String methodString = "{\"tiripPackage\":{\"service\":{\"serviceId\":\"ITAX.APP.FLK.CX.LB\",\"tranSeq\":\"1390000000020140805092628092285578\",\"tranReqDate\":\"2014-08-05 09:26:28\"},\"businessContent\":{\"request\":{\"pageSize\":\"10\",\"fgbt\":\"\",\"serviceId\":\"ITAX.APP.FLK.CX.LB\",\"fglb\":[],\"fbnd\":[0],\"pageIndex\":\"1\",\"fglx\":[]}},\"identity\":{\"user\":{\"password\":\"\",\"cert\":\"\",\"authenticateType\":\"2\",\"userId\":\"134223\"},\"application\":{\"applicationId\":\"taxoffice\",\"cert\":\"\",\"authenticateType\":\"2\",\"phoneType\":\"HUAWEI MT2-L05/android4.2/720x1208/320\",\"password\":\"123456\",\"clientNo\":\"tf0000000000006\",\"version\":\"V1.0.20140527.1\"}}}}";
			try {
				String returnJson = DynamicHttpclientCall.invoke(methodString);
				Log.i("liuz", returnJson);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
