package com.example.androidtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 多个app安装
 * 
 * @author liuzheng
 * @date 2014-10-21 下午5:06:45
 * 
 */

public class MultiAppInstallAct extends Activity {
	private ImageView baoxian_zhushou;
	ArrayList<String> packagNameList;
	private MyReceiver receiver;

	private String appName = "getphoneinfo.apk";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multi_app_install_layout);

		initpackagNameList();

		// 监听系统新安装程序的广播
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);// 注册广播机制
		filter.addDataScheme("package"); // 必须添加这项，否则拦截不到广播
		registerReceiver(receiver, filter);

		baoxian_zhushou = (ImageView) findViewById(R.id.baoxian_zhushou);
		// 主页面小模块的图标
		baoxian_zhushou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 检查是否已经安装
				Log.d("time", "clicked start " + System.currentTimeMillis()
						+ "");
				boolean installed = detectApk("com.lyf.getphoneinfo");

				if (installed) {// 已经安装直接起动
					Log.d("time",
							"getPackageManager start "
									+ System.currentTimeMillis() + "");

					Intent intent = new Intent();
					// 组件名称，第一个参数是包名，也是主配置文件Manifest里设置好的包名 第二个是类名，要带上包名
					intent.setComponent(new ComponentName("com.lyf.getphoneinfo",
							"com.lyf.getphoneinfo.MainActivity"));
					intent.setAction(Intent.ACTION_VIEW);

					Log.d("time",
							"setAction start " + System.currentTimeMillis()
									+ "");
					startActivity(intent);

				} else {// 未安装先安装
					//
					// get the cacheDir.
					File fileDir = getFilesDir();
					final String cachePath = fileDir.getAbsolutePath() + "/"
							+ appName;
					retrieveApkFromAssets(MultiAppInstallAct.this, appName,
							cachePath);
					showInstallConfirmDialog(MultiAppInstallAct.this, cachePath);
				}
			}
		});
	}

	// 捆绑安装
	public boolean retrieveApkFromAssets(Context context, String fileName,
			String path) {
		boolean bRet = false;

		try {
			File file = new File(path);
			if (file.exists()) {
				return true;
			} else {
				file.createNewFile();
				InputStream is = context.getAssets().open(fileName);
				FileOutputStream fos = new FileOutputStream(file);

				byte[] temp = new byte[1024];
				int i = 0;
				while ((i = is.read(temp)) != -1) {
					fos.write(temp, 0, i);
				}
				fos.flush();
				fos.close();
				is.close();

				bRet = true;
			}

		} catch (IOException e) {
			Toast.makeText(context, e.getMessage(), 2000).show();
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(e.getMessage());
			builder.show();
			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 * 提示用户安装程序
	 */
	public void showInstallConfirmDialog(final Context context,
			final String filePath) {
		AlertDialog.Builder tDialog = new AlertDialog.Builder(context);
		tDialog.setIcon(R.drawable.ic_launcher_01);
		tDialog.setTitle("未安装该程序");
		tDialog.setMessage("请安装该程序");

		tDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				// 修改apk权限
				try {
					String command = "chmod " + "777" + " " + filePath;
					Runtime runtime = Runtime.getRuntime();
					runtime.exec(command);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// install the apk.
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setDataAndType(Uri.parse("file://" + filePath),
						"application/vnd.android.package-archive");
				context.startActivity(intent);

			}
		});

		tDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
			}
		});

		tDialog.show();
	}

	/**
	 * 检测是否已经安装
	 * 
	 * @param packageName
	 * @return true已安装 false未安装
	 */
	private boolean detectApk(String packageName) {
		return packagNameList.contains(packageName.toLowerCase());

	}

	private void initpackagNameList() {
		// 初始化小模块列表
		packagNameList = new ArrayList<String>();
		PackageManager manager = this.getPackageManager();
		List<PackageInfo> pkgList = manager.getInstalledPackages(0);
		for (int i = 0; i < pkgList.size(); i++) {
			PackageInfo pI = pkgList.get(i);
			packagNameList.add(pI.packageName.toLowerCase());
		}

	}

	/**
	 * 
	 * 设置广播监听
	 * 
	 */
	private class MyReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {

				String packName = intent.getDataString().substring(8);

				Log.e(intent.getDataString() + "====", packName);
				// package:cn.oncomm.activity cn.oncomm.activity
				// packName为所安装的程序的包名
				packagNameList.add(packName.toLowerCase());

				// 删除file目录下的所有以安装的apk文件
				File file = getFilesDir();
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.getName().endsWith(".apk")) {
						f.delete();
					}
				}

			}
		}
	}
}
