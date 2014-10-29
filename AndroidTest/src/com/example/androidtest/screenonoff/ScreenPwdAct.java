package com.example.androidtest.screenonoff;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.main.MyApplication;

public class ScreenPwdAct extends Activity {
	
	private EditText editText;
	private Button cancleBtn, okBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.screen_pwd_layout);
		
		editText = (EditText) findViewById(R.id.screen_pwd_edittext);
		cancleBtn = (Button) findViewById(R.id.screen_pwd_button_cancle);
		okBtn = (Button) findViewById(R.id.screen_pwd_button_ok);
		
		cancleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ScreenPwdAct.this, "ÍË³ö", Toast.LENGTH_SHORT).show();
				MyApplication.getInstance().exitApplication();
			}
		});
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				okListener();
			}
		});
	}
	
	private void okListener() {
		String editStr = editText.getText().toString();
		if (editStr.length() <= 0) {
			Toast.makeText(ScreenPwdAct.this, "ÇëÊäÈëÃÜÂë", Toast.LENGTH_SHORT).show();
		}else if (!editStr.equals("1")) {
			Toast.makeText(ScreenPwdAct.this, "ÃÜÂë´íÎó£¬ÇëÖØÐÂÊäÈë£¡", Toast.LENGTH_SHORT).show();
			editText.requestFocus();
			editText.setText("");
		}else {
			finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return true;
	}
	
}
