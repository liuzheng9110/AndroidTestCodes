package com.example.androidtest;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CusDatePickerAct extends Activity implements OnClickListener{
	
	private static final int CUS_DATE_PICKER = 111;
	public static final int CUS_DATE_PICKER_Y_M_D = 222;
	public static final int CUS_DATE_PICKER_M_D = 333;
	public static final int CUS_DATE_PICKER_Y = 444;
	private Button button1, button2, button3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new  LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		button1 = new Button(this);
		button1.setId(1);
		button1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		button1.setText("年月日");
		button1.setOnClickListener(this);
		
		button2 = new Button(this);
		button2.setId(2);
		button2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		button2.setText("月日");
		button2.setOnClickListener(this);
		
		button3 = new Button(this);
		button3.setId(3);
		button3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		button3.setText("年");
		button3.setOnClickListener(this);
		
		layout.addView(button1);
		layout.addView(button2);
		layout.addView(button3);
		
		setContentView(layout);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, CusDatePickerShowAct.class);
		switch (v.getId()) {
		case 1:
			intent.putExtra("type", 1);
			break;
		case 2:
			intent.putExtra("type", 2);
			break;
		case 3:
			intent.putExtra("type", 3);
			break;

		default:
			break;
		}
		startActivityForResult(intent, CUS_DATE_PICKER);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String selDate="";
		if (data!=null) {
			selDate = data.getStringExtra("sel_date");
		}
		if (resultCode == CUS_DATE_PICKER_Y_M_D) {
			button1.setText(selDate);
		}else if (resultCode == CUS_DATE_PICKER_M_D) {
			button2.setText(selDate);
		}else if (resultCode == CUS_DATE_PICKER_Y) {
			button3.setText(selDate);
		}else {
			Toast.makeText(this, "...", Toast.LENGTH_SHORT).show();
		}
	}
}
