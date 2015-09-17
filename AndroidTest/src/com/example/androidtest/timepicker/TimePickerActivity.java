package com.example.androidtest.timepicker;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/***
 * 
 *  Class Name: TimePickerActivity.java
 *  Function: 时间选择器
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月5日 下午2:26:33
 *  @Copyright http://liuz.me 
 *  @url
 */
public class TimePickerActivity extends BaseActivity implements OnClickListener {
	
	private Button btn;
	private TimePicker timePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_picker_layout);
		
		btn = (Button) findViewById(R.id.time_picker_btn);
		btn.setOnClickListener(this);
		
		timePicker = (TimePicker) findViewById(R.id.time_picker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Log.i("liuz", "hourOfDay..." + hourOfDay + "...minute..." + minute);
			}
		});
	}

	@Override
	public void onClick(View v) {
		MyTimePickerDialog dialog = new MyTimePickerDialog(this, new OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Log.i("liuz", "hourOfDay..." + hourOfDay + "...minute..." + minute);
			}
		}, 14, 31, true);
		dialog.show();
	}
}
