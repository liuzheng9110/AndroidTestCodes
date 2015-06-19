package com.example.androidtest.barcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

/**
 * 
 *  Class Name: BarcodeZxingActivity.java
 *  Function: 二维码扫描
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年5月18日 下午3:06:50
 *  @Copyright http://liuz.me 
 *  @url https://github.com/zxing/zxing && http://www.cnblogs.com/lee0oo0/archive/2012/12/20/2826070.html
 */
public class BarcodeZxingActivity extends BaseActivity {

	private TextView resultText;
	
	private EditText inputEt;
	private ImageView imageView;
	private String inputStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_layout);

		resultText = (TextView) findViewById(R.id.result_text);
		
		inputEt = (EditText) findViewById(R.id.input);
		imageView = (ImageView) findViewById(R.id.barcode_imageview);
		imageView.setVisibility(View.GONE);
		
		imageView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				showShortToast("image long click......");
				
				return false;
			}
		});
		
	}

	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.create_barcode: // 生成二维码
			inputStr = inputEt.getText().toString();
			if (inputStr.isEmpty()) {
				showShortToast("请输入编码内容！！！");
			}else {
				try {
					Bitmap qrCodeBm = EncodingHandler.createQRCode(inputStr, 400);
					imageView.setVisibility(View.VISIBLE);
					imageView.setImageBitmap(qrCodeBm);
				} catch (WriterException e) {
					e.printStackTrace();
				}
			}
			
			break;
		case R.id.scan_barcode:// 扫描二维码
			Intent intent = new Intent(BarcodeZxingActivity.this, CaptureActivity.class);
//			startActivity(intent); // 无结果
			startActivityForResult(intent, 0);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			String result = data.getExtras().getString("result");
			resultText.setText(result);
		}
		
	}
}
