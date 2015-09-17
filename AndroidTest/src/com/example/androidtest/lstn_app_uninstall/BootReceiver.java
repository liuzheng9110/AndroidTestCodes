package com.example.androidtest.lstn_app_uninstall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// ���հ�װ�㲥
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			String packageName = intent.getDataString();
			System.out.println("��װ��:" + packageName + "�����ĳ���");
		}
		// ����ж�ع㲥
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
			String packageName = intent.getDataString();
			System.out.println("ж����:" + packageName + "�����ĳ���");
		}
	}
}
