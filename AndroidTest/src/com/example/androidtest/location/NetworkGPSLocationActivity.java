package com.example.androidtest.location;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NetworkGPSLocationActivity extends Activity {

	Context context = this;
	LinearLayout mainView = null;
	Button button = null;
	TextView tv = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("��վ+����+google���ݿⶨλ");
		mainView = new LinearLayout(this);
		mainView.setOrientation(LinearLayout.VERTICAL);
		button = new Button(this);
		button.setText("��λ����");
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				(new HttpThread(context)).start();
			}
		});
		mainView.addView(button, new LinearLayout.LayoutParams(-2, -2));
		tv = new TextView(this);
		tv.setText("Hello��\n");
		mainView.addView(tv);
		setContentView(mainView);
	}

	class HttpThread extends Thread {

		TelephonyManager tm = null;
		GsmCellLocation gcl = null;
		int cid = 0;
		int lac = 0;
		int mcc = 0;
		int mnc = 0;
		StringBuffer sb = null;

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					tv.append(sb.toString());
					break;
				}
				super.handleMessage(msg);
			}
		};

		HttpThread(Context context) {
			tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			gcl = (GsmCellLocation) tm.getCellLocation();
			cid = gcl.getCid();
			lac = gcl.getLac();
			mcc = Integer.valueOf(tm.getNetworkOperator().substring(0, 3));
			mnc = Integer.valueOf(tm.getNetworkOperator().substring(3, 5));

			sb = new StringBuffer();
			sb.append("cid:" + cid + "\n");
			sb.append("lac:" + lac + "\n");
			sb.append("mcc:" + mcc + "\n");
			sb.append("mnc:" + mnc + "\n");
		}

		public void run() {
			try {
				JSONObject jObject = new JSONObject();
				jObject.put("version", "1.1.0");
				jObject.put("host", "www.minigps.net");
				jObject.put("request_address", true);
				if (mcc == 460) {
					jObject.put("address_language", "zh_CN");
				} else {
					jObject.put("address_language", "en_US");
				}
				JSONArray jArray = new JSONArray();
				JSONObject jData = new JSONObject();
				jData.put("cell_id", cid);
				jData.put("location_area_code", lac);
				jData.put("mobile_country_code", mcc);
				jData.put("mobile_network_code", mnc);
				jArray.put(jData);
				jObject.put("cell_towers", jArray);

				DefaultHttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://www.minigps.net/map.html");
				StringEntity se = new StringEntity(jObject.toString());
				post.setEntity(se);
				HttpResponse resp = client.execute(post);
				BufferedReader br = null;
				if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					sb.append("�����ɹ�\n");
					br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
				} else {
					sb.append("������ȡ����ʧ��!\n");
				}

				String result = br.readLine();
				while (result != null) {
					sb.append(result);
					result = br.readLine();
				}
			} catch (Exception ex) {
				sb.append(ex.getMessage());
			}
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
		}
	}
}