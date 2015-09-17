package com.example.androidtest.sqlite;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

// http://www.cnblogs.com/mengdd/archive/2013/04/23/3038234.html
public class SimpleDefaultSqliteActivity extends BaseActivity {
	
	private ListView listView;
	private MyAdapter adapter;
	private Button addItemBtn;
	private ArrayList<SimpleData> simpleDatas = new ArrayList<SimpleData>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_sqlite_layout);
		setTitle("SimpleDefaultSqlite");
		
		for (int i = 0; i < 10; i++) {
			simpleDatas.add(new SimpleData("id_" + i, "name_" + i, "sex_" + i, "age_" + i));
		}
		
		adapter = new MyAdapter(this, simpleDatas);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		addItemBtn = (Button) findViewById(R.id.add_btn);
		
	}
	
	@Override
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.add_btn:
			showAddDialog();
			break;
		default:
			break;
		}
	}
	
	private void showAddDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		// 自定义alertdialog edittext 获取弹出输入框
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM); 
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		//
//		Timer timer = new Timer();  
//		timer.schedule(new TimerTask() {  
//		  
//		    @Override  
//		    public void run() {  
//		        showKeyboard(edittext);  
//		    }  
//		}, 200);
		
		final Window window = dialog.getWindow();
		window.setContentView(R.layout.sqlite_add_layout);
		
		final EditText nameEt = (EditText) window.findViewById(R.id.sqlite_add_name);
		final EditText ageEt = (EditText) window.findViewById(R.id.sqlite_add_age);
		final RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.sqlite_add_sex);
		
		Button okBtn = (Button) window.findViewById(R.id.ok_btn);
		Button cancleBtn = (Button) window.findViewById(R.id.cancle_btn);
		
		
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioButton radioButton = (RadioButton) window.findViewById(radioGroup.getCheckedRadioButtonId());
				
				String nameVal = nameEt.getText().toString();
				String ageVal = ageEt.getText().toString();
				String sexVal = radioButton.getText().toString();
				
				if (TextUtils.isEmpty(nameVal)) {
					showShortToast("nameVal is null");
					nameEt.requestFocus();
				}else if (TextUtils.isEmpty(ageVal)) {
					showShortToast("ageVal is null");
					ageEt.requestFocus();
				}else {
					Log.i("liuz", nameVal + "..." + ageVal + "..." + sexVal);
					
					dialog.dismiss();
					
					simpleDatas.add(new SimpleData(simpleDatas.size()+"", nameVal, sexVal, ageVal));
					adapter.notifyDataSetChanged();
				}
			}
		});
		
		cancleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
//	public void showKeyboard(EditText editText) {
//		if(editText!=null){
//			//设置可获得焦点
//			editText.setFocusable(true);
//			editText.setFocusableInTouchMode(true);
//			//请求获得焦点
//			editText.requestFocus();
//			//调用系统输入法
//			InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//			inputManager.showSoftInput(editText, 0);
//		}
//	}

}
