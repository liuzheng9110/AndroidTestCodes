package com.example.androidtest.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

/**
 * ���������ʾ������ܹ���ȡ��ǰλ�õľ�γ��
 * ��������û���������δ����ͨ��gps��ȡλ�� ���ȡ����locationΪ��
 * ���ǵ������û�����2.2+�İ汾 ���������ﲻ����ͨ��2.2+�ķ�ʽ�������û�����gps��λ
 * �������һֱû�кõķ��� ϣ����ҿ����ṩ��������
 * ʹ��ģ�������Ե����� ��ͨ��ddms��һ����γ�������ģ���� ������������Ϻܶ� �ҾͲ�����
 * by Garretly
 * blog  http://blog.csdn.net/garretly
 */
public class LocationActivity extends Activity {
	TextView tv1;
	Location location;
	LocationManager lm;
	LocationListener locationListener;
	//ͨ��network��ȡlocation
	private String networkProvider = LocationManager.NETWORK_PROVIDER;
	//ͨ��gps��ȡlocation
	private String GpsProvider = LocationManager.GPS_PROVIDER;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_layout);

		// ����UI���
		Button b1 = (Button) findViewById(R.id.button1);
		tv1 = (TextView) findViewById(R.id.textView1);
		//��ʼִ�л�ȡlocation����
		initLocation(this);
		//���������
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//ʵ����δ����ٴθ�����tv�е���Ϣ
				initLocation(LocationActivity.this);
			}
		});
	}
	
	//��ȡlocation����
	private void initLocation(Context mContext){
		//���ϵͳ�������  LocationManager ����  ����������ôд ���ÿ���
		lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		
		//���ȼ�� ͨ��network �ܷ���location����,��������location���� �����tv
		if (startLocation(networkProvider,mContext)) {
			updateLocation(location,mContext);
		}else if(startLocation(GpsProvider,mContext)){  
			//ͨ��gps �ܷ���location����,��������location���� �����tv
			updateLocation(location,mContext);
		}else{
			//����������ַ��������ܻ��location���� ����ʾ������Ϣ
			Toast.makeText(this, "û�д�GPS�豸", 5000).show();
		}
	}
	/**
	 * ͨ������ ��ȡLocation����
	 * ���Location����Ϊ�� �򷵻� true ���Ҹ�ֵ��ȫ�ֱ��� location
	 *   ���Ϊ�� ����false ����ֵ��ȫ�ֱ���location
	 * 
	 * @param provider
	 * @param mContext
	 * @return
	 */
	private boolean startLocation(String provider,final Context mContext){
//		Location location = lm.getLastKnownLocation(provider);
		
		// λ�ü�����
		locationListener = new LocationListener() {
			// ��λ�øı�ʱ����
			@Override
			public void onLocationChanged(Location location) {
				System.out.println("onLocationChanged..." + location.toString());
				updateLocation(location,mContext);
			}

			// ProviderʧЧʱ����
			@Override
			public void onProviderDisabled(String arg0) {
				System.out.println("onProviderDisabled..." + arg0);
			}

			// Provider����ʱ����
			@Override
			public void onProviderEnabled(String arg0) {
				System.out.println("onProviderEnabled..." + arg0);
			}

			// Provider״̬�ı�ʱ����
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				System.out.println("onStatusChanged...");
			}
		};

		// 500�������һ�Σ�����λ�ñ仯
		lm.requestLocationUpdates(provider, 500, 0, locationListener);
		
//		���Location����Ϊ�� �򷵻� true ���Ҹ�ֵ��ȫ�ֱ��� location
//		���Ϊ�� ����false ����ֵ��ȫ�ֱ���location
		if (location!= null) {
			this.location=location;
			return true;
		}
		return false;
		
	}
	// ����λ����Ϣ չʾ��tv��
	private void updateLocation(Location location,Context mContext) {
		if (location != null) {
			tv1.setText("��λ������Ϣ���£�" + location.toString() + "/n/t���о��ȣ�"
					+ location.getLongitude() + "/n/t����γ�ȣ�"
					+ location.getLatitude());
			//����Ѿ���ȡ��location��Ϣ ��������ע��location�ļ���
			//gps����һ��ʱ�����Զ��ر�
			lm.removeUpdates(locationListener);
		} else {
			System.out.println("û�л�ȡ����λ����Location");
		}
	}

	protected void onDestroy() {
		//�����activity����ʱ  ������ע��location�ļ���
		//gps����һ��ʱ�����Զ��ر�
		lm.removeUpdates(locationListener);
		
		super.onDestroy();
	}
}