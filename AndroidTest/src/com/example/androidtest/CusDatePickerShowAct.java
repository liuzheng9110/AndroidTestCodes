package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CusDatePickerShowAct extends Activity implements OnClickListener{
	
	private LinearLayout yearLay;
	private Button yearAdd, yearSub;
	private TextView yearShowTv;
	private LinearLayout monthLay;
	private Button monthAdd, monthSub;
	private TextView monthShowTv;
	private LinearLayout dayLay;
	private Button dayAdd, daySub;
	private TextView dayShowTv;
	private Button confirmBtn, cancleBtn;
	
	private int type;// 1 年月日  2 月日 3 年
	private String selDate = "";
	private int resultCode = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cus_date_picker_show);
		
		getIntentData();
		
		initView();
		
	}

	private void getIntentData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		type = intent.getIntExtra("type", 0);
	}

	private void initView() {
		// TODO Auto-generated method stub
		yearLay = (LinearLayout) findViewById(R.id.year_lay);
		yearAdd = (Button) findViewById(R.id.year_add);
		yearShowTv = (TextView) findViewById(R.id.year_tv);
		yearSub = (Button) findViewById(R.id.year_sub);
		
		monthLay = (LinearLayout) findViewById(R.id.month_lay);
		monthAdd = (Button) findViewById(R.id.month_add);
		monthShowTv = (TextView) findViewById(R.id.month_tv);
		monthSub = (Button) findViewById(R.id.month_sub);
		
		dayLay = (LinearLayout) findViewById(R.id.day_lay);
		dayAdd = (Button) findViewById(R.id.day_add);
		dayShowTv = (TextView) findViewById(R.id.day_tv);
		daySub = (Button) findViewById(R.id.day_sub);
		
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancleBtn = (Button) findViewById(R.id.cancle_btn);
		
		yearAdd.setOnClickListener(this);
		yearSub.setOnClickListener(this);
		monthAdd.setOnClickListener(this);
		monthSub.setOnClickListener(this);
		dayAdd.setOnClickListener(this);
		daySub.setOnClickListener(this);
		
		confirmBtn.setOnClickListener(this);
		cancleBtn.setOnClickListener(this);
		
		if (type==2) {
			yearLay.setVisibility(View.GONE);
		}else if (type==3) {
			monthLay.setVisibility(View.GONE);
			dayLay.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.year_add:
			addOper(yearShowTv, 1);
			break;
		case R.id.year_sub:
			subOper(yearShowTv, 1);
			break;
		case R.id.month_add:
			addOper(monthShowTv, 2);
			break;
		case R.id.month_sub:
			subOper(monthShowTv, 2);
			break;
		case R.id.day_add:
			addOper(dayShowTv, 3);
			break;
		case R.id.day_sub:
			subOper(dayShowTv, 3);
			break;
		//////////////////////////////////////
		case R.id.confirm_btn:
			if (type==1) {
				selDate = yearShowTv.getText().toString() +"-"+monthShowTv.getText().toString()+"-"+dayShowTv.getText().toString();
				resultCode = CusDatePickerAct.CUS_DATE_PICKER_Y_M_D;
			}else if (type==2) {
				selDate = monthShowTv.getText().toString()+"-"+dayShowTv.getText().toString();
				resultCode = CusDatePickerAct.CUS_DATE_PICKER_M_D;
			}else if (type==3) {
				selDate = yearShowTv.getText().toString();
				resultCode = CusDatePickerAct.CUS_DATE_PICKER_Y;
			}
			Intent intent = new Intent();
			intent.putExtra("sel_date", selDate);
			setResult(resultCode, intent);
			this.finish();
			break;
		case R.id.cancle_btn:
			Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
			this.finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 加
	 * @param viewShowTv 
	 * @param i 
	 */
	private void addOper(TextView viewShowTv, int i) {
		int viewVal = Integer.parseInt(viewShowTv.getText().toString());
		viewVal++;
		if (i==2) {// 月
			if (viewVal==13) {
				viewVal=1;
			}
		}
		if (i==3) {// 日
			if (viewVal==32) {
				viewVal=1;
			}
		}
		if (viewVal<10) {
			viewShowTv.setText("0"+viewVal);
		}else {
			viewShowTv.setText(viewVal+"");
		}
	}
	/**
	 * 减
	 * @param viewShowTv
	 * @param i 
	 */
	private void subOper(TextView viewShowTv, int i) {
		int viewVal = Integer.parseInt(viewShowTv.getText().toString());
		viewVal--;
		if (i==2) {// 月
			if (viewVal==0) {
				viewVal=12;
			}
		}
		if (i==3) {// 日
			if (viewVal==0) {
				viewVal=31;
			}
		}
		if (viewVal<10) {
			viewShowTv.setText("0"+viewVal);
		}else {
			viewShowTv.setText(viewVal+"");
		}
	}
}
