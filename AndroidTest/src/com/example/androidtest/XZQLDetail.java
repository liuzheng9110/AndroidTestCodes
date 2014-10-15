package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XZQLDetail extends Activity {

	private LinearLayout topLayout, centerLayout, bottomLayout;

	private LayoutInflater inflater;

	// ///
	private LinearLayout zfcgLayout;
	private LinearLayout infoTechProLayout;
	private LinearLayout meetPathLayoutNotByOfficeLayout;
	private LinearLayout officialAdmitNotLogistLayout;
	
	// ///
	private TextView zfcgUseUnit, zfcgByYear, zfcgFee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.xzql_detail);

		inflater = LayoutInflater.from(this);

		topLayout = (LinearLayout) findViewById(R.id.table_lay1);
		centerLayout = (LinearLayout) findViewById(R.id.table_lay2);
		bottomLayout = (LinearLayout) findViewById(R.id.table_lay3);

		// initZFCGLayout();
		// initInfoTechProLayout();
		// initMeetPathNotByOfficeLayout();
		initOfficialAdmitNotByLogisticsLayout();

		// if (zfcgLayout!=null) {
		// centerLayout.removeAllViews();
		// centerLayout.addView(zfcgLayout);
		//
		// zfcgUseUnit.setText("长沙市地税局信息中心");
		// zfcgByYear.setText("2014年");
		// zfcgFee.setText("186500元");
		//
		// zfcgFee.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// startActivity(new Intent(XZQLDetail.this, ZFCGDetail.class));
		// }
		// });
		// }

		removeAddView(officialAdmitNotLogistLayout);
	}

	private void removeAddView(LinearLayout layout) {
		if (layout != null) {
			centerLayout.removeAllViews();
			centerLayout.addView(layout);
		}
	}

	// 信息化建设立项
	private void initInfoTechProLayout() {
		infoTechProLayout = (LinearLayout) inflater.inflate(R.layout.info_tech_pro_desc, null);
	}

	// 政府采购
	private void initZFCGLayout() {
		zfcgLayout = (LinearLayout) inflater.inflate(R.layout.zfcg_desc, null);

		zfcgUseUnit = (TextView) zfcgLayout.findViewById(R.id.zfcg_user_unit_tv);
		zfcgByYear = (TextView) zfcgLayout.findViewById(R.id.zfcg_by_year_tv);
		zfcgFee = (TextView) zfcgLayout.findViewById(R.id.zfcg_fee_tv);
	}

	// 会议管理流程(非办公室发起)
	private void initMeetPathNotByOfficeLayout() {
		meetPathLayoutNotByOfficeLayout = (LinearLayout) inflater.inflate(R.layout.meet_path_n_office_desc, null);
	}

	// 公务接待流程(非后勤发起)
	private void initOfficialAdmitNotByLogisticsLayout() {
		officialAdmitNotLogistLayout = (LinearLayout) inflater.inflate(R.layout.official_admit_n_logist_desc, null);
	}
}
