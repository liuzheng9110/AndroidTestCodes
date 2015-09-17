package com.example.androidtest.datetimepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: DateTimePickerActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月5日 下午3:35:07
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DateTimePickerActivity extends BaseActivity implements OnClickListener {
	
	private Button dateTimeBtn;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date_time_picker_layout);
		
		dateTimeBtn = (Button) findViewById(R.id.date_time_picker);
		dateTimeBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.date_time_picker);
		datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);
		timePicker = (TimePicker) dialog.findViewById(R.id.time_picker);
		timePicker.setIs24HourView(true);
		
		dialog.setTitle("选择日期时间");
		dialog.show();
	}
}
