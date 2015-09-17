package com.example.androidtest.numberpicker;

import java.util.Calendar;

import org.jsoup.select.Evaluator.IsNthChild;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.androidtest.R;

/**
 * 
 *  Class Name: NumberPickerActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月22日 下午1:02:00
 *  @Copyright http://liuz.me 
 *  @url
 */
public class NumberPickerActivity extends FragmentActivity {
	@Bind(R.id.numberPicker1)
	NumberPicker numberPicker1;
	@Bind (R.id.numberPicker2)
	NumberPicker numberPicker2;
	@Bind(R.id.numberPicker3)
	NumberPicker numberPicker3;
	@Bind(R.id.numberPicker4)
	NumberPicker numberPicker4;
	
	private int hour, min;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.number_picker_layout);
		ButterKnife.bind(this);
		
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		min = calendar.get(Calendar.MINUTE);
		
		numberPicker1.setMaxValue(24);
		numberPicker1.setMinValue(0);
		numberPicker1.setFocusable(true);
		numberPicker1.setFocusableInTouchMode(true);
		numberPicker1.setActivated(true);
		numberPicker1.setFormatter(new NumberPicker.Formatter() {
			@Override
			public String format(int value) {
				if (value < 10) {
					return "0" + value;
				}
				return value+"";
			}
		});
		numberPicker1.setValue(hour);
		
		numberPicker2.setMaxValue(59);
		numberPicker2.setMinValue(0);
//		numberPicker1.setFocusable(true);
//		numberPicker1.setFocusableInTouchMode(true);
//		numberPicker1.setActivated(true);
		numberPicker2.setFormatter(new NumberPicker.Formatter() {
			@Override
			public String format(int value) {
				if (value < 10) {
					return "0" + value;
				}
				return value+"";
			}
		});
		numberPicker2.setValue(min);
		
//		numberPicker3.setVisibility(View.GONE);
//		numberPicker4.setVisibility(View.GONE);
	}
	
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.btn:
			Toast.makeText(this, numberPicker1.getValue() + " : " + numberPicker2.getValue(), Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.btn_show:
//			DialogFragment dialogFragment = new TimePickerFragment();
//			dialogFragment.show(getSupportFragmentManager(), "timepicker");
			break;

		default:
			break;
		}
	}
	
}
