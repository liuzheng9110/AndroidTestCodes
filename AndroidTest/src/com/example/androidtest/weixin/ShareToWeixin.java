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
//		intent.setAction(Intent.ACTION_SEND); // 单图
		intent.setAction(Intent.ACTION_SEND_MULTIPLE); // 多图
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
			showShortToast("请确认手机是否已安装维修！");
		}
	}

	/**
	 * 通过包名检测系统中是否安装某个应用程序
	 * 
	 * @param context
	 * @param packageName
	 *            ：应用程序的包名(QB:com.tencent.mtt)
	 * @return true : 系统中已经安装该应用程序
	 * @return false : 系统中未安装该应用程序
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
