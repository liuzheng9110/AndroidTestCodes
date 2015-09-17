package com.example.androidtest.weixin;

import java.io.File;
import java.util.ArrayList;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

public class ShareToWeixin extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixin_layout);

	}

	public void click_listener(View v) {
		share();
	}

	private void share() {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"));
//		intent.setAction(Intent.ACTION_SEND); // ��ͼ
		intent.setAction(Intent.ACTION_SEND_MULTIPLE); // ��ͼ
		intent.setType("image/*");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.putExtra("Kdescription", "share msg...");
//		Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/smart_share.png"));
//		intent.putExtra(Intent.EXTRA_STREAM, uri);
		
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        imageUris.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/smart_share.png")));
        imageUris.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/smart_share.png")));
        imageUris.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/smart_share.png")));
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);

		boolean flag = checkApkExist(this, "com.tencent.mm");
		if (flag) {
			startActivity(intent);
//			startActivity(Intent.createChooser(intent, "xxxxxx"));
		}else {
			showShortToast("��ȷ���ֻ��Ƿ��Ѱ�װά�ޣ�");
		}
	}

	/**
	 * ͨ���������ϵͳ���Ƿ�װĳ��Ӧ�ó���
	 * 
	 * @param context
	 * @param packageName
	 *            ��Ӧ�ó���İ���(QB:com.tencent.mtt)
	 * @return true : ϵͳ���Ѿ���װ��Ӧ�ó���
	 * @return false : ϵͳ��δ��װ��Ӧ�ó���
	 * */
	boolean checkApkExist(Context context, String packageName) {
		if (packageName == null || "".equals(packageName)) {
			return false;
		}
		try {
			context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

}
