package com.example.androidtest.camera_save;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: CameraSaveActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年7月30日 下午9:00:33
 *  @Copyright http://liuz.me 
 *  @url 
 */
public class CameraSaveActivity extends BaseActivity implements OnClickListener {

	private ImageView imageView;
	private LinearLayout imageFileLayout;
	private ImageView imageFile1, imageFile2, imageFile3, imageFile4;

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	private Bitmap mBitmap;
	private File cacheFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.camera_save_layout);

		imageView = (ImageView) findViewById(R.id.add_camera);
		imageView.setOnClickListener(this);
		imageFileLayout = (LinearLayout) findViewById(R.id.tax_collect_files_image_layout);
		imageFile1 = (ImageView) findViewById(R.id.image_file1);
		imageFile2 = (ImageView) findViewById(R.id.image_file2);
		imageFile3 = (ImageView) findViewById(R.id.image_file3);
		imageFile4 = (ImageView) findViewById(R.id.image_file4);
		
		Log.i("liuz", "CacheDir..." + getExternalCacheDir());  	// CacheDir.../storage/emulated/0/Android/data/com.example.androidtest/cache
		Log.i("liuz", "CacheDir..." + getFilesDir()); 			// CacheDir.../data/data/com.example.androidtest/files
		Log.i("liuz", "CacheDir..." + getCacheDir());			// CacheDir.../data/data/com.example.androidtest/cache

		cacheFile = getCacheDir();
		fileUri = CameraUtils.getOutputMediaFileUri(CameraUtils.MEDIA_TYPE_IMAGE, cacheFile);

		Log.i("liuz", "fileUri..." + fileUri);
	}

	@Override
	public void onClick(View v) {
		starCamera();
	}

	private void starCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // 指定文件保存路径
		// start the Video Capture Intent
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				// Image captured and saved to fileUri specified in the Intent
				if (data != null) {
					Bundle bundle = data.getExtras();
					mBitmap = (Bitmap) bundle.get("data");
					
					if (mBitmap != null) {
						showShortToast("Image saved to:\n" + fileUri);
						Log.i("liuz", "...");
						
						imageFile1.setImageBitmap(mBitmap);
	                }else {
	                	Log.i("liuz", "fail...");
					}
				}else if (fileUri != null){ // 从自定义保存路径中获取图片进行显示
					imageFile1.setImageURI(fileUri);
				}else {
					Log.i("liuz", "fail...");
				}
			} else if (resultCode == RESULT_CANCELED) {
				showShortToast("user cancled...");
			} else {
				
			}
		}
	}
	
	
	
}
