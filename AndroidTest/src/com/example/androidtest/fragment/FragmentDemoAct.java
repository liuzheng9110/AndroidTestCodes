package com.example.androidtest.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidtest.R;

public class FragmentDemoAct extends FragmentActivity {
	
	
	private FragmentManager fManager;
	private FragmentTransaction transaction;
	
	private FragmentList fragmentList;
	private FragmentDetail fragmentDetail;
	
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_demo_lay);
		
		button = (Button) findViewById(R.id.fragment_button);
		
		fManager = getSupportFragmentManager();

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (fManager.findFragmentByTag("fragList")==null) {
					fragmentList = new FragmentList();
					transaction = fManager.beginTransaction();
					transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
					transaction.replace(R.id.content_left,fragmentList,"fragList").commit();
				}
			}
		});
	}
	
	public void showDetail(int position) {
		if (fManager.findFragmentByTag("fragDetail")==null) {
			fragmentDetail = new FragmentDetail(position);
			
			transaction = fManager.beginTransaction();
			transaction.replace(R.id.content_left, fragmentDetail,"fragDetail").commit();
			fManager.executePendingTransactions();
		}
	}
}
