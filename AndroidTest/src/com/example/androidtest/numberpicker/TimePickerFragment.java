package com.example.androidtest.numberpicker;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements OnTimeSetListener {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar calendar = Calendar.getInstance();
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this	, hourOfDay, minute, false);
		return timePickerDialog;
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		
	}
}
