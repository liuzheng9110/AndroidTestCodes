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
		case R.id.http_webservice:
			doWebserviceRequest("³¤É³");
			break;
		}
	}
	
	private void doWebserviceRequest(String cityName) {
		String URL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx";
		String NAME_SPACE = "http://WebXml.com.cn";
		String METHOD_NAME = "getWeatherbyCityName";
		String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
		
		try {
			SoapObject soapObject = new SoapObject(NAME_SPACE, METHOD_NAME);
			soapObject.addProperty("theCityName", cityName);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(soapObject);
			
			HttpTransportSE htSe = new HttpTransportSE(URL);
			htSe.debug = true;
			htSe.call(SOAP_ACTION, envelope);
			
			SoapObject response = (SoapObject) envelope.getResponse();
			doWebserviceResponse(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void doWebserviceResponse(SoapObject response) {
		System.out.println(response.toString());
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
