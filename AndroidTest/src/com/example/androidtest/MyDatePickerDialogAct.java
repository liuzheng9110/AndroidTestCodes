package com.example.androidtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

public class MyDatePickerDialogAct extends Activity {

	private MyDatePickerDialog mDialog;
	private Button btnView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_datepicker_dialog);
		btnView = (Button) findViewById(R.id.dp);

		btnView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDateDialog();
			}
		});
	}

	protected void showDateDialog() {
		final Calendar cal = Calendar.getInstance();
		mDialog = new MyDatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						btnView.setText(year + "-" + (monthOfYear + 1));
					}
				}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		
		mDialog.show();
		
		mDialog.setTitle("请选择日期");
		
		/// 隐藏月份
		DatePicker dp = findDatePicker((ViewGroup) mDialog.getWindow().getDecorView());
		if (dp != null) {
			((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * @param group
	 * @return
	 */
	private DatePicker findDatePicker(ViewGroup group) {
		if (group != null) {
			for (int i = 0, j = group.getChildCount(); i < j; i++) {
				View child = group.getChildAt(i);
				if (child instanceof DatePicker) {
					return (DatePicker) child;
				} else if (child instanceof ViewGroup) {
					DatePicker result = findDatePicker((ViewGroup) child);
					if (result != null)
						return result;
				}
			}
		}
		return null;
	}

}
