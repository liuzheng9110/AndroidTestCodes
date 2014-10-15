package com.example.androidtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ShowDialogAct extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.show_dialog_lay, null);
		
		Button btn01 = new Button(this);
		btn01.setId(1);
		btn01.setText("1");
		btn01.setOnClickListener(this);
		
		layout.addView(btn01);

		setContentView(layout);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 1:
			showDialog(1);
			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			return new AlertDialog.Builder(ShowDialogAct.this)
					.setTitle("Title")
					.setMessage("This is message")
					.setPositiveButton("确定", null)
					.setNegativeButton("取消", null)
					.create();
		default:
			break;
		}
		return super.onCreateDialog(id);
	}

}
