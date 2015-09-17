package com.example.androidtest.sqlite.greendao;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.OnItemClick;

import com.example.androidtest.R;
import com.example.androidtest.ShowDialogAct;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.sqlite.SimpleData;
import com.example.androidtest.sqlite.greendao.bean_dao.DaoMaster;
import com.example.androidtest.sqlite.greendao.bean_dao.DaoSession;
import com.example.androidtest.sqlite.greendao.bean_dao.DaoMaster.DevOpenHelper;
import com.example.androidtest.sqlite.greendao.bean_dao.UserInfo;
import com.example.androidtest.sqlite.greendao.bean_dao.UserInfoDao;

public class SimpleUseGreendaoSqliteActivity extends BaseActivity {
	private ListView listView;
	private GreenDaoAdapter adapter;
	private ArrayList<UserInfo> simpleDatas = new ArrayList<UserInfo>();
	
	private final String DB_NAME = "greendao_db";
	private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private UserInfoDao userInfoDao;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("SimpleUseGreendaoSqlite");
		setContentView(R.layout.simple_sqlite_layout);
		
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		userInfoDao = daoSession.getUserInfoDao();
		
//		for (int i = 0; i < 10; i++) {
//			userInfoDao.insert(new UserInfo(null, "name_" + i, "sex_" + i, "age_" + i));
//		}
		
		simpleDatas = (ArrayList<UserInfo>) userInfoDao.loadAll();
		
		adapter = new GreenDaoAdapter(this, simpleDatas);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				UserInfo userInfo = (UserInfo) adapter.getItem(position);
				
				showOperDialog(userInfo);
			}
		});
	}

	protected void showOperDialog(final UserInfo userInfo) {
		new AlertDialog.Builder(this)
		.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				userInfoDao.deleteByKey(userInfo.getId());
				notifyData();
			}
		})
		.setNegativeButton("Update", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();
	}

	@Override
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.add_btn:
			showAddDialog();
			break;
		}
	}
	
	private void showAddDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this, R.style.MyAlertDialog).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		// 自定义alertdialog edittext 获取弹出输入框
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM); 
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		final Window window = dialog.getWindow();
		window.setContentView(R.layout.sqlite_add_layout);
		
		final EditText nameEt = (EditText) window.findViewById(R.id.sqlite_add_name);
		final EditText ageEt = (EditText) window.findViewById(R.id.sqlite_add_age);
		final RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.sqlite_add_sex);
		
		Button okBtn = (Button) window.findViewById(R.id.ok_btn);
		Button cancleBtn = (Button) window.findViewById(R.id.cancle_btn);
		
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioButton radioButton = (RadioButton) window.findViewById(radioGroup.getCheckedRadioButtonId());
				
				String nameVal = nameEt.getText().toString();
				String ageVal = ageEt.getText().toString();
				String sexVal = radioButton.getText().toString();
				
				if (TextUtils.isEmpty(nameVal)) {
					showShortToast("nameVal is null");
					nameEt.requestFocus();
				}else if (TextUtils.isEmpty(ageVal)) {
					showShortToast("ageVal is null");
					ageEt.requestFocus();
				}else {
					dialog.dismiss();
					long id = userInfoDao.insert(new UserInfo(null, nameVal, sexVal, ageVal));
					if (id > 0) {
						notifyData();
					}
				}
			}
		});
		
		cancleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
	private void notifyData() {
		simpleDatas.clear();
		simpleDatas.addAll(userInfoDao.loadAll());
		adapter.notifyDataSetChanged();
	}
	
}
