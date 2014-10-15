package com.example.androidtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class CusDialogAct extends Activity implements OnClickListener, OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.FILL_PARENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));

		Button button = new Button(this);
		button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		button.setText("click show cus dialog");
		button.setOnClickListener(this);
		button.setOnTouchListener(this);

		layout.addView(button);
		setContentView(layout);

	}

	@Override
	public void onClick(View v) {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		Window window = dialog.getWindow();
		window.setContentView(R.layout.cus_dialog);
		
		Button confirmBtn = (Button) window.findViewById(R.id.add_btn);
		Button cancleBtn = (Button) window.findViewById(R.id.cancle_btn);
		
		confirmBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CusDialogAct.this.finish();
			}
		});
		cancleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.i("liuz", "...........");
		return false;
	}
	
}
